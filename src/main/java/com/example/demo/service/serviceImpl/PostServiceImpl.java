package com.example.demo.service.serviceImpl;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    // Add post
    @Override
    public Post addPost(Post post, User user) {
        post.setAuthor(user);
        return postRepository.save(post);
    }

    // Show all posts
    @Override
    public List<Post> allPosts() {
        return postRepository.findAll();
    }
}
