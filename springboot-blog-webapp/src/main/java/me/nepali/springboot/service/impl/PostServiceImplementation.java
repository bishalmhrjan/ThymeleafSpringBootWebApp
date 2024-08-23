package me.nepali.springboot.service.impl;

import me.nepali.springboot.dto.PostDTO;
import me.nepali.springboot.entity.Post;
import me.nepali.springboot.mapper.PostMapper;
import me.nepali.springboot.repository.PostRepository;
import me.nepali.springboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImplementation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDTO> findAllPosts() {
        List<Post> posts=postRepository.findAll();
        return posts.stream().map(PostMapper::mapToPostDTO)
                .collect(Collectors.toList());
    }
}