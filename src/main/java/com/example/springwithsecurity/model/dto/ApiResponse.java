package com.example.springwithsecurity.model.dto;

import com.example.springwithsecurity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse{
    private String status;
    private Pagination pagination;
    private List<User> users;
}
