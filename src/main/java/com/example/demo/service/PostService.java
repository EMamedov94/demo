package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.model.User;

import java.util.List;

public interface PostService {
    Post addPost(Post post, User user);
    List<Post> allPosts();
}
