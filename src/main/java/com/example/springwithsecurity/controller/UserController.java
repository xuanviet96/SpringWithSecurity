package com.example.springwithsecurity.controller;

import com.example.springwithsecurity.entity.api.AuthResponse;
import com.example.springwithsecurity.entity.api.LoginRequest;
import com.example.springwithsecurity.security.JwtTokenUtil;
import com.example.springwithsecurity.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()));
            // Gen token
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);

            List<String> roles = jwtTokenUtil.getClaims(token);

            AuthResponse authResponse = new AuthResponse(userDetails.getUsername(), token, roles);
            return ResponseEntity.ok().body(authResponse);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @GetMapping("/api/greeting")
//    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
//    @Secured("ROLE_USER")
    public ResponseEntity<String> greeting(){
        String message = "this enpoint for user only";
        return ResponseEntity.ok().body(message);
    }
    @GetMapping("/api/hello")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
//    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> hello(){
        String message = "this enpoint for admin only";
        return ResponseEntity.ok().body(message);
    }
}
