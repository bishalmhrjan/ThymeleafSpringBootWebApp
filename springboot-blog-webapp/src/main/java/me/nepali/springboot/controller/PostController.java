package me.nepali.springboot.controller;

import jakarta.validation.Valid;
import me.nepali.springboot.dto.CommentDTO;
import me.nepali.springboot.dto.PostDTO;
import me.nepali.springboot.entity.Post;
import me.nepali.springboot.service.CommentService;
import me.nepali.springboot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private PostService postService;
    private CommentService commentService;


    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService= commentService;
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

    //handler method to handle list comments request
    @GetMapping("/admin/posts/comments")
    public String postComments(Model model){
        List<CommentDTO> comments = commentService.findAllComments();
        model.addAttribute("comments",comments);
        return "admin/comments";

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

    //handler method to handle edit post form Submit Request
    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId, @Valid @ModelAttribute("post") PostDTO post, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("post",post);
            return "admin/edit-post";
        }
        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/admin/posts";
    }


    //handle method to handle delte post request
    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId){
    postService.deletePost(postId);

    return "redirect:/admin/posts";
    }


    //handler method to handle view post request
    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl, Model model){
        PostDTO postDTO = postService.finPostByUrl(postUrl);
        model.addAttribute("post",postDTO);
        return "admin/view-post";

    }

    //handler method to handle search blog posts requests
    //localhost:8080/admin/posts/search?query=java
    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value="query") String query, Model model) {

        List<PostDTO> posts= postService.searchPosts(query);
        model.addAttribute("posts",posts);
        return "admin/posts";
    }

    //handler method to handle delete comment requests
    @GetMapping("/admin/posts/comments/{commentId}")
    public String deleteComment(@PathVariable("commentId")Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/admin/posts/comments";
    }
}
