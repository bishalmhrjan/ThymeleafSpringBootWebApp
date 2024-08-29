package me.nepali.springboot.service;

import me.nepali.springboot.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    void createComment(String postUrl, CommentDTO commentDTO);

    List<CommentDTO> findAllComments();

    void deleteComment(Long commentId);
}
