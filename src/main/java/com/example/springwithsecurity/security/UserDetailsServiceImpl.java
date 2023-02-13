package com.example.springwithsecurity.security;

import com.example.springwithsecurity.entity.User;
import com.example.springwithsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user != null) {
            return new UserDetailsImpl(user);
        } else {
            throw  new UsernameNotFoundException("User with email" + username + " does not exist!");
        }
    }
}
