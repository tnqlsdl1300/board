package com.subin.board.springboot.web.dto;

import com.subin.board.springboot.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 게시글 전체조회를 위한 Dto
@Getter
@NoArgsConstructor  // mybatis용 dto는 기본생성자가 없으면 안됨
public class PostsListResponseDto {
    private long id;
    private String title;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
