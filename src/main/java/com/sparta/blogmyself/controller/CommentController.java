package com.sparta.blogmyself.controller;

import com.sparta.blogmyself.domain.Comment;
import com.sparta.blogmyself.dto.CommentRequestDto;
import com.sparta.blogmyself.repository.CommentRepository;
import com.sparta.blogmyself.security.UserDetailsImpl;
import com.sparta.blogmyself.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;


    @PostMapping("/api/comments")
    public Comment createComment(@RequestBody CommentRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails){
        // 로그인 되어 있는 회원 테이블의 ID
        return commentService.createComment(requestDto, userDetails);
    }

    @GetMapping("/api/comments")
    public List<Comment> readComment(){
        return commentRepository.findAllByOrderByModifiedAtDesc();
    }


    @PutMapping("/api/comments/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.update(id, userDetails.getUser().getId(), requestDto);
        return id;
    }
    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public Long deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.delete(id, userDetails.getUser().getId());
        return id;
    }
}
