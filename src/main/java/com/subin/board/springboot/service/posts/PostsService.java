package com.subin.board.springboot.service.posts;

import com.subin.board.springboot.domain.posts.PostsRepository;
import com.subin.board.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestDto requestDto){

        // 1. requestDto.toEntity() 는 수정된 Posts(Entity)룰 return 해줌
        // 2. postsRepository.save(requestDto.toEntity()) 는 수정된 Posts를 뜻함
        // 3. postsRepository.save(requestDto.toEntity()).getId()는 수정된 Posts의 id를 받아옴
        return postsRepository.save(requestDto.toEntity()).getId();
    }

}
