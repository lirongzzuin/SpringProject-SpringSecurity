package com.sparta.blogmyself.controller;

import com.sparta.blogmyself.domain.Post;
import com.sparta.blogmyself.repository.PostRepository;
import com.sparta.blogmyself.dto.PostRequestDto;
import com.sparta.blogmyself.security.UserDetailsImpl;
import com.sparta.blogmyself.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;


    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        // 로그인 되어 있는 회원 테이블의 ID
        return postService.createPost(requestDto, userDetails);
    }

    @GetMapping("/api/posts")
    public List<Post> readPost(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.update(id, userDetails.getUser().getId(), requestDto);
        return id;
    }
// 게시글 삭제
    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.delete(id, userDetails.getUser().getId());
        return id;
    }
// 유저네임으로 게시글 조회
    @GetMapping("/api/usernames/{username}/posts")
    public List<Post> singlePost(@PathVariable String username){
        return postRepository.findAllByUsername(username);
    }

    @GetMapping("/api/posts/{id}")
    public Optional<Post> detailPost(@PathVariable Long id){
        return postRepository.findById(id);
    }
}
