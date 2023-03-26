package com.example.springwithsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class SpringwithSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringwithSecurityApplication.class, args);
	}

}
