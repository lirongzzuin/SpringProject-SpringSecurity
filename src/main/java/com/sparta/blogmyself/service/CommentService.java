package com.sparta.blogmyself.service;

import com.sparta.blogmyself.domain.Comment;
import com.sparta.blogmyself.dto.CommentRequestDto;
import com.sparta.blogmyself.repository.CommentRepository;
import com.sparta.blogmyself.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    public Comment createComment(CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Comment comment = new Comment(userDetails.getUsername(), requestDto.getComment(), userDetails.getUser().getId());

        commentRepository.save(comment);

        return comment;
    }

    @Transactional
    public Long update(Long id, Long userId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
        if (comment.getUserId().equals(userId)) {
            comment.update(requestDto, id);
            commentRepository.save(comment);
            return id;
        } else {
            throw new IllegalArgumentException("수정 불가능한 댓글입니다.");
        }
    }

    public Long delete(Long id, Long userId) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
        if (comment.getUserId().equals(userId)) {
            comment.delete(id);
            commentRepository.delete(comment);
            return id;
        } else {
            throw new IllegalArgumentException("삭제 불가능한 댓글입니다.");
        }
    }
}
