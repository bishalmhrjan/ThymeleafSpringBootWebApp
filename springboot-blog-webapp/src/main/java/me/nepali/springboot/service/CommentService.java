package me.nepali.springboot.service;

import me.nepali.springboot.dto.CommentDTO;

public interface CommentService {
    void createComment(String postUrl, CommentDTO commentDTO);
}
