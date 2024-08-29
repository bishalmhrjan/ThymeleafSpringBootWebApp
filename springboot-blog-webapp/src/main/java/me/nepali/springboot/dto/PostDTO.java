package me.nepali.springboot.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.nepali.springboot.entity.Comment;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
 //post and postdto contain same attributes
    private Long id;
    @NotEmpty(message = "Post title should not be empty")
    private String title;
    private String url;
    @NotEmpty(message = "Post content should not be empty")
    private String content;

    @NotEmpty(message = "Post shortDescription should not be empty")
    private String shortDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updateOn;
    private Set<CommentDTO> comments;


}
