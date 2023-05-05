package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AuthorizeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizeController {
    private final AuthorizeService authorizeService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/registrationForm")
    public ResponseEntity<String> registrationForm(@Valid @RequestBody User user,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Ошибка валидации", HttpStatus.BAD_REQUEST);
        }
        if (authorizeService.emailExists(user.getEmail())) {
            return new ResponseEntity<>("Такой email уже зарегистрирован", HttpStatus.BAD_REQUEST);
        }
        if (authorizeService.usernameExists(user.getUsername())) {
            return new ResponseEntity<>("Такой логин уже зареган", HttpStatus.BAD_REQUEST);
        }
        authorizeService.registrationNewUser(user);
        return new ResponseEntity<>("Успешная регистрация", HttpStatus.OK);
    }

    @PostMapping("/loginForm")
    public ResponseEntity<String> loginForm(@Valid @RequestBody User user,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Ошибка валидации", HttpStatus.BAD_REQUEST);
        }

        authorizeService.loadUserByUsername(user.getUsername());
        return new ResponseEntity<>("Успешный вход", HttpStatus.OK);
    }
}
