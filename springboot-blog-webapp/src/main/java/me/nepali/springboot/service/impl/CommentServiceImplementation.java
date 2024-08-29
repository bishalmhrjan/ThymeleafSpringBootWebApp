package me.nepali.springboot.service.impl;

import me.nepali.springboot.dto.CommentDTO;
import me.nepali.springboot.entity.Comment;
import me.nepali.springboot.entity.Post;
import me.nepali.springboot.mapper.CommentMapper;
import me.nepali.springboot.repository.CommentRepository;
import me.nepali.springboot.repository.PostRepository;
import me.nepali.springboot.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImplementation implements CommentService {
   private CommentRepository commentRepository;
   private PostRepository postRepository;

    public CommentServiceImplementation(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDTO commentDTO) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDTO);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDTO> findAllComments() {
        List<Comment> comments = commentRepository.findAll();

        return comments.stream().map(CommentMapper :: mapToCommentDTO)
                .collect(Collectors.toList());
    }

}
