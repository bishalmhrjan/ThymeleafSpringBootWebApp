package me.nepali.springboot.controller;

import me.nepali.springboot.dto.PostDTO;
import me.nepali.springboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class BlogController {
     private PostService postService;


    public BlogController(PostService postService) {
        this.postService = postService;
    }

    //handle method to handle http://loclhost:8080
    @GetMapping("/")
    public  String viewBlogPost(Model model){

    List<PostDTO> postResponse = postService.findAllPosts();
    model.addAttribute("postsResponse",postResponse);
    return "blog/view-posts";

    }
}
