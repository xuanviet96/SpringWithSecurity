package com.example.springwithsecurity.service;

import com.example.springwithsecurity.entity.User;
import com.example.springwithsecurity.model.dto.UserDTO;
import com.example.springwithsecurity.model.request.ChangePasswordRequest;
import com.example.springwithsecurity.model.request.CreateUserRequest;
import com.example.springwithsecurity.model.request.UpdateProfileRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getListUsers();

    Page<User> adminListUserPages(String fullName, String phone, String email, Integer page);

    User createUser(CreateUserRequest createUserRequest);

    void changePassword(User user, ChangePasswordRequest changePasswordRequest);

    User updateProfile(User user, UpdateProfileRequest updateProfileRequest);


}
