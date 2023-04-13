package com.example.springwithsecurity.controller;

import com.example.springwithsecurity.entity.User;
import com.example.springwithsecurity.model.dto.ApiResponse;
import com.example.springwithsecurity.model.dto.Pagination;
import com.example.springwithsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.springwithsecurity.config.Contant.LIMIT_USER;

@RestController
public class AdminUserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/api/admin/users/list")
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
}
