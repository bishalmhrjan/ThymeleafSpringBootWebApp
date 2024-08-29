package me.nepali.springboot.mapper;

import me.nepali.springboot.dto.PostDTO;
import me.nepali.springboot.entity.Post;

import java.util.stream.Collectors;

public class PostMapper {
    // map Post entity to PostDTO

    public static PostDTO mapToPostDTO(Post post){
        PostDTO postDTO = PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .url(post.getUrl())
                .shortDescription(post.getShortDescription())
                .createdOn(post.getCreatedOn())
                .updateOn(post.getUpdateOn())
                .comments(post.getComments().stream().map(comment ->
                        CommentMapper.mapToCommentDTO(comment))
                        .collect(Collectors.toSet()))
                .build();
       return postDTO;
    }

    //map PostDTO to Post Entity
    public static Post mapToPost(PostDTO postDTO){
        return Post.builder()
                .id(postDTO.getId())
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .url(postDTO.getUrl())
                .shortDescription(postDTO.getShortDescription())
                .createdOn(postDTO.getCreatedOn())
                .updateOn(postDTO.getUpdateOn())
                .build();
    }
}
