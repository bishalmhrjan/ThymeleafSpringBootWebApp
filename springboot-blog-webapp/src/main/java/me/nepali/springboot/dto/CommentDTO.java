package me.nepali.springboot.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import me.nepali.springboot.entity.Post;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty(message="email should not be empty or null")
    private String email;

    @NotEmpty(message = "content should not be empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updateOn;
    private Post post;

}
