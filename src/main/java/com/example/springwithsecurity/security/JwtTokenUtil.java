package com.example.springwithsecurity.security;

import org.springframework.beans.factory.annotation.Value;

public class JwtTokenUtil {
    @Value("604800")
    public Integer duration;

    @Value("secret")
    private String secret;

    public String generateToken(UserDetailsImpl userDetails) {

    }
}
