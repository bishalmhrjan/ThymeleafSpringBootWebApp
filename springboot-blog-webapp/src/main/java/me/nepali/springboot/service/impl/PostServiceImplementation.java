package me.nepali.springboot.service.impl;

import me.nepali.springboot.dto.PostDTO;
import me.nepali.springboot.entity.Post;
import me.nepali.springboot.entity.User;
import me.nepali.springboot.mapper.PostMapper;
import me.nepali.springboot.repository.PostRepository;
import me.nepali.springboot.repository.UserRepository;
import me.nepali.springboot.service.PostService;
import org.springframework.stereotype.Service;
import me.nepali.springboot.util.SecurityUtils;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostServiceImplementation(PostRepository postRepository,
                           UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PostDTO> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostMapper::mapToPostDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> findPostsByUser() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Post> posts = postRepository.findPostsByUser(userId);
        return posts.stream()
                .map((post) -> PostMapper.mapToPostDTO(post))
                .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDTO postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Post post = PostMapper.mapToPost(postDto);
        post.setCreatedBy(user);
        postRepository.save(post);
    }

    @Override
    public PostDTO findPostById(Long postId) {
        Post post = postRepository.findById(postId).get();
        return PostMapper.mapToPostDTO(post);
    }

    @Override
    public void updatePost(PostDTO postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Post post = PostMapper.mapToPost(postDto);
        post.setCreatedBy(createdBy);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDTO finPostByUrl(String postUrl) {
        return null;
    }


    @Override
    public PostDTO findPostByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).get();
        return PostMapper.mapToPostDTO(post);
    }

    @Override
    public List<PostDTO> searchPosts(String query) {
        List<Post> posts = postRepository.searchPosts(query);
        return posts.stream()
                .map(PostMapper::mapToPostDTO)
                .collect(Collectors.toList());
    }
}


