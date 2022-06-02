package com.sparta.blogmyself.domain;

import com.sparta.blogmyself.dto.PostRequestDto;
import com.sparta.blogmyself.security.UserDetailsImpl;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Post extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long userId;


    public Post(String title, String username, String password, String contents, Long userId) {
        this.title = title;
        this.username = username;
        this.password = password;
        this.contents = contents;
        this.userId = userId;
    }

    public Post(PostRequestDto requestDto, Long userId) {
        // 관심상품을 등록한 회원 Id 저장
        this.userId = userId;
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }


    public void update(PostRequestDto requestDto, Long userId){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.userId = userId;
    }

    public void delete(Long userId){
        this.userId = userId;
    }
}
