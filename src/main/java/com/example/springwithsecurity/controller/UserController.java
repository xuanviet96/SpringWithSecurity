package com.example.springwithsecurity.controller;

import com.example.springwithsecurity.entity.api.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()));
            // Gen token
            String token = jwtTokenUtil.
        } catch (Exception ex) {
            throw new BadCredentialsException("Email hoặc mật khẩu không chính xác!");
        }
    }
}
