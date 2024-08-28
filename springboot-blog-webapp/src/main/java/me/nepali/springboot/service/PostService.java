package me.nepali.springboot.service;

import me.nepali.springboot.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> findAllPosts();

    void createPost(PostDTO postDTO);

    PostDTO findPostById(Long id);

    void updatePost(PostDTO postDTO);

    void deletePost(Long post);

    PostDTO finPostByUrl(String postUrl);
    List<PostDTO> searchPosts(String query);

}
