package com.example.springwithsecurity.service;

import com.example.springwithsecurity.entity.User;
import com.example.springwithsecurity.model.dto.UserDTO;
import com.example.springwithsecurity.model.request.ChangePasswordRequest;
import com.example.springwithsecurity.model.request.CreateUserRequest;
import com.example.springwithsecurity.model.request.UpdateProfileRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {
    Page<UserDTO> getListUserDTOs(String fullName, String phone, String email, String address, Integer page, String sortField, Sort.Direction sortDirection);

    Page<User> adminListUserPages(String fullName, String phone, String email, String address, Integer page, String field, Sort.Direction sortDirection);

    User createUser(CreateUserRequest createUserRequest);
    User getUserById(long id);

    void changePassword(User user, ChangePasswordRequest changePasswordRequest);

    User updateProfile(Long id, UpdateProfileRequest updateProfileRequest);

    User getUserByToken(String token);

    void deleteUserById(Long id);
}
