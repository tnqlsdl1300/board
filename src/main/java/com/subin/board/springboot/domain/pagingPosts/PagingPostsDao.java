package com.subin.board.springboot.domain.pagingPosts;

import com.subin.board.springboot.utils.Criteria;
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

    // 페이징 게시글 가져오기
    @Override
    public List<PagingPostsDto> findAll(Criteria cri) {
        return sqlSession.selectList("getPagingPosts", cri);
    }

    // 게시글 총 개수 구하기
    @Override
    public int countTotalPosts() {
        return sqlSession.selectOne("countTotalPosts");
    }

}
