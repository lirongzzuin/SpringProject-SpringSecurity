package com.sparta.blogmyself.controller;

import com.sparta.blogmyself.domain.Post;
import com.sparta.blogmyself.domain.PostRepository;
import com.sparta.blogmyself.domain.PostRequestDto;
import com.sparta.blogmyself.service.PostService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto){
        Post post =new Post(requestDto);
        return postRepository.save(post);
    }

    @GetMapping("/api/posts")
    public List<Post> readPost(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        postService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        return id;
    }

    @GetMapping("/api/usernames/{username}/posts")
    public List<Post> singlePost(@PathVariable String username){
        return postRepository.findAllByUsername(username);
    }
}
