package com.sparta.blogmyself.service;

import com.sparta.blogmyself.domain.Post;
import com.sparta.blogmyself.repository.PostRepository;
import com.sparta.blogmyself.dto.PostRequestDto;
import com.sparta.blogmyself.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    public Post createPost(PostRequestDto requestDto, UserDetailsImpl userDetails) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Post post = new Post(requestDto.getTitle(), userDetails.getUsername(), userDetails.getPassword(), requestDto.getContents(), userDetails.getUser().getId());

        postRepository.save(post);

        return post;
    }

    @Transactional
    public Long update(Long id, Long userId, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );
        if (post.getUserId().equals(userId)) {
            post.update(requestDto, id);
            postRepository.save(post);
            return id;
        }  else{
            throw new IllegalArgumentException("수정 불가능한 게시물입니다.");
        }
    }
    public Long delete(Long id, Long userId) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 포스팅입니다.")
        );
        if (post.getUserId().equals(userId)) {
            post.delete(id);
            postRepository.delete(post);
            return id;
        }  else{
            throw new IllegalArgumentException("삭제 불가능한 게시물입니다.");
        }
    }

    // 회원 ID 로 등록된 상품 조회
//    public List<Post> getPost(Long userId) {
//        return postRepository.findAllByUserId(userId);
//    }
}
