package com.example.springwithsecurity.service.impl;

import com.example.springwithsecurity.entity.User;
import com.example.springwithsecurity.exception.BadRequestException;
import com.example.springwithsecurity.exception.InternalServerException;
import com.example.springwithsecurity.model.dto.UserDTO;
import com.example.springwithsecurity.model.mapper.UserMapper;
import com.example.springwithsecurity.model.request.ChangePasswordRequest;
import com.example.springwithsecurity.model.request.CreateUserRequest;
import com.example.springwithsecurity.model.request.UpdateProfileRequest;
import com.example.springwithsecurity.repository.UserRepository;
import com.example.springwithsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<UserDTO> getListUsers() {
        return null;
    }

    @Override
    public Page<User> adminListUserPages(String fullName, String phone, String email, Integer page) {
        return null;
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest) {

        User user = userRepository.findByEmail(createUserRequest.getEmail());
        if(user != null) {
            throw new BadRequestException("User email already exist !");
        }
        user = UserMapper.toUser(createUserRequest);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        try {
            userRepository.save(user);
        } catch (Exception ex) {
            throw new InternalServerException("Lỗi khi thêm user");
        }
        return user;
    }

    @Override
    public void changePassword(User user, ChangePasswordRequest changePasswordRequest) {
        //Check password
        if(!BCrypt.checkpw(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("The old password is not correct !");
        }
        String hash = BCrypt.hashpw(changePasswordRequest.getNewPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
        userRepository.save(user);
    }

    @Override
    public User updateProfile(User user, UpdateProfileRequest updateProfileRequest) {
        return null;
    }

}
