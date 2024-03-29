package com.example.springwithsecurity.service.impl;

import com.example.springwithsecurity.entity.Role;
import com.example.springwithsecurity.entity.User;
import com.example.springwithsecurity.exception.BadRequestException;
import com.example.springwithsecurity.exception.InternalServerException;
import com.example.springwithsecurity.exception.NotFoundException;
import com.example.springwithsecurity.model.dto.UserDTO;
import com.example.springwithsecurity.model.mapper.UserMapper;
import com.example.springwithsecurity.model.request.ChangePasswordRequest;
import com.example.springwithsecurity.model.request.CreateUserRequest;
import com.example.springwithsecurity.model.request.UpdateProfileRequest;
import com.example.springwithsecurity.repository.RoleRepository;
import com.example.springwithsecurity.repository.UserRepository;
import com.example.springwithsecurity.security.JwtTokenUtil;
import com.example.springwithsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.springwithsecurity.config.Contant.LIMIT_USER;
import static com.example.springwithsecurity.config.Contant.USER_ROLE;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserDTO> getListUserDTOs(String fullName, String phone, String email, String address, Integer page, String sortField, Sort.Direction sortDirection) {
        page--;
        if (page < 0) {
            page = 0;
        }
        Sort sort = Sort.by(sortDirection, sortField);
        Pageable pageable = PageRequest.of(page, LIMIT_USER, sort);
        Page<User> usersPage = userRepository.adminListUserPages(fullName, phone, email, address, pageable);
        int totalPage = usersPage.getTotalPages();
        if (page > totalPage) {
            pageable = PageRequest.of(totalPage - 1, LIMIT_USER, sort);
        } else {
            pageable = PageRequest.of(page, LIMIT_USER, sort);
        }
        usersPage = userRepository.adminListUserPages(fullName, phone, email, address, pageable);
        List<UserDTO> userDTOs = usersPage.getContent()
                .stream()
                .map(user -> UserMapper.toUserDTO(user))
                .collect(Collectors.toList());
        return new PageImpl<>(userDTOs, pageable, usersPage.getTotalElements());
    }

    @Override
    public Page<User> adminListUserPages(String fullName, String phone, String email, String address, Integer page, String sortField, Sort.Direction sortDirection) {
        page--;
        if (page < 0) {
            page = 0;
        }
        Sort sort = Sort.by(sortDirection, sortField);
        Pageable pageable = PageRequest.of(page, LIMIT_USER, sort);
        Page<User> usersPage = userRepository.adminListUserPages(fullName, phone, email, address, pageable);
        int totalPage = usersPage.getTotalPages();
        if (page > totalPage) {
            pageable = PageRequest.of(totalPage - 1, LIMIT_USER, sort);
        } else {
            pageable = PageRequest.of(page, LIMIT_USER, sort);
        }
        return userRepository.adminListUserPages(fullName, phone, email, address, pageable);
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        String roleName = USER_ROLE;
        Optional<Role> roleUser = roleRepository.findByRoleName(roleName);

        if (roleUser.isEmpty()) {
            throw new NotFoundException("User role not found !");
        }
        Optional<User> optionalUser = userRepository.findByEmail(createUserRequest.getEmail());
        if (optionalUser.isPresent()) {
            throw new BadRequestException("User email already exist !");
        }
        User user = UserMapper.toUser(createUserRequest);
        user.setRoles(new HashSet<>(List.of(roleUser.get())));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        try {
            userRepository.save(user);
        } catch (Exception ex) {
            throw new InternalServerException("Lỗi khi thêm user");
        }
        return user;
    }

    @Override
    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User id is not exist");
        }
        return user.get();
    }

    @Override
    public void changePassword(User user, ChangePasswordRequest changePasswordRequest) {
        //Check password
        if (!BCrypt.checkpw(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("The old password is not correct !");
        }
        String hash = BCrypt.hashpw(changePasswordRequest.getNewPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
        userRepository.save(user);
    }

    @Override
    public User updateProfile(Long id, UpdateProfileRequest updateProfileRequest) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()) {
            throw new NotFoundException("User with id:" + id + " not found");
        }
        User user = optionalUser.get();
        user.setPhone(updateProfileRequest.getPhone());
        user.setFullName(updateProfileRequest.getFullName());
        user.setAddress(updateProfileRequest.getAddress());
        user.setModifiedAt(new Timestamp(System.currentTimeMillis()));
        return user;
    }

    @Override
    public User getUserByToken(String token) {
        String username = jwtTokenUtil.getSubject(token);
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new NotFoundException("user does not exist");
        }
        return user.get();
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User id is not exist");
        }
        try {
            userRepository.delete(user.get());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }


}
