package com.sparta.blogmyself.service;

import com.sparta.blogmyself.domain.Post;
import com.sparta.blogmyself.domain.PostRepository;
import com.sparta.blogmyself.domain.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );
        if (post.getPassword().equals(requestDto.getPassword())) {
            post.update(requestDto);
            return id;
        }  else{
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
