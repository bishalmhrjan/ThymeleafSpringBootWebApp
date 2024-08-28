package me.nepali.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
 //post and postdto contain same attributes
    private Long id;
    private String title;
    private String url;
    private String content;

    private String shortDescription;
    private LocalDateTime createdOn;
    private LocalDateTime updateOn;


}
