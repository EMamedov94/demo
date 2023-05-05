package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.service.ActionService;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final PostService postService;
    private final ActionService actionService;

    // Add post
    @PostMapping("/addPost")
    public ResponseEntity<String> addPost(@AuthenticationPrincipal User user, @RequestBody Post post) {
        postService.addPost(post, user);
        return new ResponseEntity<>("Пост добавлен", HttpStatus.OK);
    }

    // Like
    @PatchMapping("/like/{id}")
    public ResponseEntity<String> like(@AuthenticationPrincipal User user, @PathVariable Long id) {
        if (user == null) {
            return new ResponseEntity<>("Нужна авторизация", HttpStatus.BAD_REQUEST);
        }
        actionService.like(id, user);
        return new ResponseEntity<>("Лайк", HttpStatus.OK);
    }
}
