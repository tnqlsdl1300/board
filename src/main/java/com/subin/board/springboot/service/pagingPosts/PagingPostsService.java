package com.subin.board.springboot.service.pagingPosts;

import com.subin.board.springboot.domain.pagingPosts.InterPagingPostsDao;
import com.subin.board.springboot.utils.Criteria;
import com.subin.board.springboot.web.dto.PagingPostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PagingPostsService {

    private final InterPagingPostsDao pagingPostsDao;

    public List<PagingPostsDto> findAll(Criteria cri){
        return pagingPostsDao.findAll(cri);
    }

}
