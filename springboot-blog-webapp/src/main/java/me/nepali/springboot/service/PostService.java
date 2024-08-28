package me.nepali.springboot.service;

import me.nepali.springboot.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> findAllPosts();

    void createPost(PostDTO postDTO);

    PostDTO findPostById(Long id);
}
