package me.nepali.springboot.controller;

import jakarta.validation.Valid;
import me.nepali.springboot.dto.PostDTO;
import me.nepali.springboot.entity.Post;
import me.nepali.springboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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


    // Handler method to handle new post request

    @GetMapping("admin/posts/newPost")
    public String newPostForm(Model model){
        PostDTO postDTO= new PostDTO();
        model.addAttribute("post",postDTO);
        return "admin/create-post";
    }

    // hanler method to handle form submit request
    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post")  PostDTO postDTO, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("post",postDTO);
            return "admin/create-post";
        }
        postDTO.setUrl(getUrl(postDTO.getTitle()));
        postService.createPost(postDTO);
        System.out.println("title of post is "+postDTO.getTitle());
        return "redirect:/admin/posts";

    }

    private static String getUrl(String postTitle){
        //OOps concets explained in java
        // oops-concepts-.explained-in-java
        String title =postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+","-");
        url=url.replaceAll("[^A-Za-z0-9]","-");
        return url;
    }


    //handler method to handle edit post request
    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId, Model model){
        PostDTO postDTO = postService.findPostById(postId);
        model.addAttribute("post",postDTO);
        return "admin/edit-post";
    }

}
