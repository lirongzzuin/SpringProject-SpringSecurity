package com.sparta.blogmyself.domain;

import com.sparta.blogmyself.dto.CommentRequestDto;
import com.sparta.blogmyself.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Long userId;



    public Comment(String username, String comment, Long userId) {
        this.username = username;
        this.comment = comment;
        this.userId = userId;
    }

    public void update(CommentRequestDto requestDto, Long userId){
        this.comment = requestDto.getComment();
        this.userId = userId;
    }

    public void delete(Long userId){
        this.userId = userId;
    }
}
