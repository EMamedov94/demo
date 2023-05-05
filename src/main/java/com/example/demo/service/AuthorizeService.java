package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthorizeService {
    void registrationNewUser(User user);
    boolean emailExists(String email);
    boolean usernameExists(String username);
    UserDetails loadUserByUsername(String username);
}
