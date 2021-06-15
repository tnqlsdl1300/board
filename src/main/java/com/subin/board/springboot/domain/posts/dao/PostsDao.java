package com.subin.board.springboot.domain.posts.dao;

import com.subin.board.springboot.domain.posts.Posts;
import com.subin.board.springboot.domain.posts.PostsMapper;
import com.subin.board.springboot.web.dto.PostsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// mybatis 용 dao

@RequiredArgsConstructor
@Repository
public class PostsDao implements PostsMapper {
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<PostsListResponseDto> getPostsList() {
        List<PostsListResponseDto> list = sqlSession.selectList("getPostsList");
        return list;
    }
}
