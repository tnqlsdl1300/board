package com.subin.board.springboot.web.dto;

import com.subin.board.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // DB 테이블 값이 변경되는 부분 -> Entity를 수정했기 때문
    public Posts toEntity(){

        // Posts를 return
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}
