package com.subin.board.springboot.web.dto;

import com.subin.board.springboot.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PagingPostsDto {
    private long id;
    private String title;
    private String author;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
