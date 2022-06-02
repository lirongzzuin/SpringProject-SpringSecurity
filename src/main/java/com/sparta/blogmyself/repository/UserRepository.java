package com.sparta.blogmyself.repository;

import com.sparta.blogmyself.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.security.util.Password;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByKakaoId(Long kakaoId);
}