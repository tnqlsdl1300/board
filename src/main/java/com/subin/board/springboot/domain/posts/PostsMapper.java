package com.subin.board.springboot.domain.posts;

import com.subin.board.springboot.web.dto.PostsListResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// mybatis 용 mapper 연결 인터페이스
public interface PostsMapper {
    List<PostsListResponseDto> getPostsList();
}
