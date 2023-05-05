package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PageController {
    private final PostService postService;

    @GetMapping("/")
    public ResponseEntity<String> index(@AuthenticationPrincipal User user) {
        postService.allPosts();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
