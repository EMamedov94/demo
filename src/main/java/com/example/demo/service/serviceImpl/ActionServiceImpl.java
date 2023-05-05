package com.example.demo.service.serviceImpl;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {
    private final PostRepository postRepository;


    // Like
    @Override
    public void like(Long id, User user) {
        Post post = postRepository.findById(id).orElse(null);
        User likedUser = post.getUsersLiked().stream()
                .filter(e -> e.getId().equals(user.getId())).findFirst().orElse(null);

        if (likedUser == null) {
            post.setLikes(post.getLikes() + 1);
            post.getUsersLiked().add(user);
        } else {
            post.setLikes(post.getLikes() - 1);
            post.getUsersLiked().remove(likedUser);
        }
        postRepository.save(post);
    }
}
