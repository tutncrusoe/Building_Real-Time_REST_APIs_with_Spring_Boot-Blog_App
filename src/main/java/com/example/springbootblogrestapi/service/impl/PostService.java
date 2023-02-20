package com.example.springbootblogrestapi.service.impl;

import com.example.springbootblogrestapi.entity.Post;
import com.example.springbootblogrestapi.payload.PostDto;
import com.example.springbootblogrestapi.repository.PostRepository;
import org.springframework.stereotype.Service;

public interface PostService {
    PostDto createPost(PostDto postDto);
}

@Service
class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO to Entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post newPost = postRepository.save(post);

        // convert Entity to DTO
        PostDto postResponse = new PostDto();
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }
}
