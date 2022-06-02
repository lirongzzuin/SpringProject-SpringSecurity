package com.sparta.blogmyself.repository;

import com.sparta.blogmyself.domain.Comment;
import com.sparta.blogmyself.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByModifiedAtDesc();
}
