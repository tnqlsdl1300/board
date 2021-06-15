package com.subin.board.springboot.domain.pagingPosts;

import com.subin.board.springboot.web.dto.PagingPostsDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PagingPostsDao implements InterPagingPostsDao {

    private final SqlSessionTemplate sqlSession;

    @Override
    public List<PagingPostsDto> findAll() {
        return sqlSession.selectList("getPagingPosts");
    }

}
