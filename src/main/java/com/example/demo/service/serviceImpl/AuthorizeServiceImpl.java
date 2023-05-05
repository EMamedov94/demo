package com.example.demo.service.serviceImpl;

import com.example.demo.enums.RoleEnum;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthorizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizeServiceImpl implements UserDetailsService, AuthorizeService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Check email exists
    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    // Check username exists
    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    // Login user
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    // Registration new user
    @Override
    public void registrationNewUser(User user) {
        user.setRole(RoleEnum.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
