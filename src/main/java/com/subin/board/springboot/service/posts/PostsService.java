package com.subin.board.springboot.service.posts;

import com.subin.board.springboot.domain.posts.Posts;
import com.subin.board.springboot.domain.posts.PostsRepository;
import com.subin.board.springboot.web.dto.PostsListResponseDto;
import com.subin.board.springboot.web.dto.PostsResponseDto;
import com.subin.board.springboot.web.dto.PostsSaveRequestDto;
import com.subin.board.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    // 게시글 생성
    @Transactional
    public long save(PostsSaveRequestDto requestDto){

        // 1. requestDto.toEntity() 는 수정된 Posts(Entity)룰 return 해줌
        // 2. postsRepository.save(requestDto.toEntity()) 는 수정된 Posts를 뜻함
        // 3. postsRepository.save(requestDto.toEntity()).getId()는 수정된 Posts의 id를 받아옴
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // 게시글 수정
    @Transactional
    public long update(long id, PostsUpdateRequestDto requestDto) {

        // 1: id로 DB에서 검색을 해봄으로써 DB에 접촉한 entity가 생성됨
        // -> 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태 
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // 2: 생성된 entity를 가지고 값을 변경 -> 따로 update 쿼리를 날리지 않아도 됨 -> 더티체킹
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
        // 3: 트랜잭션 처리가 끝나면서 변경된 entity가 DB에 반영
    }

    // 게시글 조회
    @Transactional(readOnly = true)
    public PostsResponseDto findById(long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
    
    // 게시글 삭제
    @Transactional
    public void deleteById(long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        postsRepository.delete(posts);
    }

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        
        // findAllDesc(): postsRepository에 직접 작성한 쿼리
        // 대충 List<Posts>로 넘어온 값을 List<PostsListResponseDto>로 변경시켜주는 코드
        // .map(PostsListResponseDto::new)은 .map(Posts -> new PostsListResponseDto(Posts))와 같다
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
