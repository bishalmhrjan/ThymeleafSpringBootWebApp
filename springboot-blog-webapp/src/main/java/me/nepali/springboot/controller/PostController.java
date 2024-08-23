package me.nepali.springboot.controller;

import me.nepali.springboot.dto.PostDTO;
import me.nepali.springboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PostController {
    private PostService postService;


    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create handler method , GET Request and return model and view
    @GetMapping("/admin/posts")
    public String posts(Model model){
        List<PostDTO> posts= postService.findAllPosts();
        model.addAttribute("posts",posts);
        return "/admin/posts"; // admin is folder
    }

}
