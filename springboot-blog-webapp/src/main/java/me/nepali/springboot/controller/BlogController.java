package me.nepali.springboot.controller;

import me.nepali.springboot.dto.CommentDTO;
import me.nepali.springboot.dto.PostDTO;
import me.nepali.springboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    //handler method to handle view Post request

    @GetMapping("/post/{postUrl}")
    private String showPost(@PathVariable("postUrl") String postUrl, Model model){
    PostDTO postDto = postService.finPostByUrl(postUrl);
    CommentDTO commentDTO = new CommentDTO();

    model.addAttribute("post",postDto);
    model.addAttribute("comment",commentDTO);
    return "blog/blog-post";
    }

    //handler method to handle blog post search request
    @GetMapping("/page/search") public String searchPosts(@RequestParam(value="query")String query, Model model){
      List<PostDTO> postsResponse = postService.searchPosts(query);
      model.addAttribute("postsResponse",postsResponse);
      return "blog/view-posts";
    }
}
