package com.subin.board.springboot.domain.pagingPosts;

import com.subin.board.springboot.web.dto.PagingPostsDto;

import java.util.List;

public interface InterPagingPostsDao {
    List<PagingPostsDto> findAll();
}
