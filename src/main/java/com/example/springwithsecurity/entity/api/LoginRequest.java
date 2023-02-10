package com.example.springwithsecurity.entity.api;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequest {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
