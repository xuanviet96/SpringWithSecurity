package com.example.springwithsecurity.controller;

import com.example.springwithsecurity.entity.User;
import com.example.springwithsecurity.model.dto.ApiResponse;
import com.example.springwithsecurity.model.dto.Pagination;
import com.example.springwithsecurity.model.request.CreateUserRequest;
import com.example.springwithsecurity.model.request.UpdateProfileRequest;
import com.example.springwithsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;
import java.sql.Timestamp;

import static com.example.springwithsecurity.config.Contant.LIMIT_USER;

@RestController
public class AdminUserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/api/admin/users")
    public ResponseEntity<Object> getListUserPages(@RequestParam(defaultValue = "", required = false) String fullName,
                                                   @RequestParam(defaultValue = "", required = false) String phone,
                                                   @RequestParam(defaultValue = "", required = false) String email,
                                                   @RequestParam(defaultValue = "", required = false) String address,
                                                   @RequestParam(defaultValue = "1", required = false) Integer page,
                                                   @RequestParam(defaultValue = "created_at", required = false) String sortField,
                                                   @RequestParam(defaultValue = "ASC", required = false) Sort.Direction sortDirection){
        Page<User> users = userService.adminListUserPages(fullName, phone, email, address, page, sortField, sortDirection);
        Pagination pagination;
        pagination = new Pagination((int)users.getTotalElements(), LIMIT_USER, page, users.getTotalPages());
        ApiResponse response = new ApiResponse("success", pagination, users.getContent());
        return ResponseEntity.ok(response);
    }
    @GetMapping(value = "/api/admin/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @PostMapping(value = "/api/admin/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User user = userService.createUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @PutMapping(value = "/api/admin/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateProfileRequest updateProfileRequest) {
        User user = userService.getUserById(id);
        user.setPhone(updateProfileRequest.getPhone());
        user.setFullName(updateProfileRequest.getFullName());
        user.setAddress(updateProfileRequest.getAddress());
        user.setModifiedAt(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(user);
    }
    @DeleteMapping(value = "/api/admin/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
