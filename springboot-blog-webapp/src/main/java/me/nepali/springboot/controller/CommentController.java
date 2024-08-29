package me.nepali.springboot.controller;

import jakarta.validation.Valid;
import me.nepali.springboot.dto.CommentDTO;
import me.nepali.springboot.dto.PostDTO;
import me.nepali.springboot.service.CommentService;
import me.nepali.springboot.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    private CommentService commentService;
    private PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }


    @PostMapping("/{postUrl}/comments")
    public String createComment(@PathVariable("postUrl") String postUrl,
                                @Valid  @ModelAttribute("comment")CommentDTO commentDTO,
                                BindingResult result,
                                Model model){
        PostDTO postDTO = postService.finPostByUrl(postUrl);
        if(result.hasErrors()){

            model.addAttribute("post", postDTO);
            model.addAttribute("comment",commentDTO);
            return "blog/blog-post";
        }
        commentService.createComment(postUrl,commentDTO);
        return "redirect:/post/"+postUrl;


    }
}
