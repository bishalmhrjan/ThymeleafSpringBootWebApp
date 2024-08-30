package me.nepali.springboot.mapper;

import me.nepali.springboot.dto.CommentDTO;
import me.nepali.springboot.entity.Comment;

public class CommentMapper {
    //convert comment entity to comment dto

    public static CommentDTO mapToCommentDTO(Comment comment){
        CommentDTO commentDTO= CommentDTO.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .content(comment.getContent())
                .createdOn(comment.getCreatedOn())
                .updateOn(comment.getUpdateOn())
                .build();
        return commentDTO;
    }

    //convert comment dto to comment entity

    public static Comment mapToComment(CommentDTO commentDTO){
        Comment comment = Comment.builder()
                .id(commentDTO.getId())
                .name(commentDTO.getName())
                .email(commentDTO.getEmail())
                .content(commentDTO.getContent())
                .createdOn(commentDTO.getCreatedOn())
                .updateOn(commentDTO.getUpdateOn())
                .build();
        return comment;

    }


}
