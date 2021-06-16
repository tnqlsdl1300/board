package com.subin.board.springboot.domain.pagingPosts;

import com.subin.board.springboot.utils.Criteria;
import com.subin.board.springboot.web.dto.PagingPostsDto;

import java.util.List;

public interface InterPagingPostsDao {

    // 페이징 게시글 가져오기
    List<PagingPostsDto> findAll(Criteria cri);

    // 게시글 총 개수 구하기
    int countTotalPosts();

}
