package com.sparta.blogmyself.repository;

import com.sparta.blogmyself.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
    List<Post> findAllByUsername(String username);
    List<Post> findAllByUserId(Long userId);
    Optional<Post> findById(Long id);

}
