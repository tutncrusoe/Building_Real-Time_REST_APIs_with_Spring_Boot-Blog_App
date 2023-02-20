package com.example.springbootblogrestapi.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
}
