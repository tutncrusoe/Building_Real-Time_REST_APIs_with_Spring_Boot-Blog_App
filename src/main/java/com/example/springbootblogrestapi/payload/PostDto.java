package com.example.springbootblogrestapi.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class PostDto {
    private long id;

    // title should not be null or empty
    // title should have at least 2 characters
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    // description should not be null or empty
    // description should have at least 10 characters
    @NotEmpty
    @Size(min = 5, message = "Post description should have at least 10 characters")
    private String description;

    // content should not be null or empty
    @NotEmpty
    private String content;

    private Set<CommentDto> comments;
}
