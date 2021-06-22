package com.subin.board.springboot.web;

import com.subin.board.springboot.config.auth.LoginUser;
import com.subin.board.springboot.config.auth.dto.SessionUser;
import com.subin.board.springboot.service.posts.PostsService;
import com.subin.board.springboot.web.dto.PostsResponseDto;
import com.subin.board.springboot.web.dto.PostsSaveRequestDto;
import com.subin.board.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    /*
    - Controller에서 view단과 통신할때 꼭 return 타입을 지정해줘야 한다. void X
        - void로 할 시, 쿼리는 실행되지만 view단에서 실행했는지 모름
        - 등록: id(PK)
        - 수정: id(PK)
        - 조회: Dto
        - 삭제: id(PK)
     */

    // 게시글 등록
    @PostMapping("/api/v1/posts")
    public long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    // 게시글 수정
    @PutMapping("/api/v1/posts/{id}")
    public long update(@PathVariable long id, @RequestBody PostsUpdateRequestDto requestDto, @LoginUser SessionUser user){
        // 작성자와 로그인한 회원의 이름이 같은지 확인
        if (user.getName().equals(requestDto.getAuthor())){
            return postsService.update(id, requestDto);
        }else{
            return -999;
        }

    }
    
    // 게시글 조회
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable long id){
        return postsService.findById(id);
    }

    // 게시글 삭제
    @DeleteMapping("/api/v1/posts/{id}")
    public long deleteById(@PathVariable long id){
        postsService.deleteById(id);

        return id;
    }

}
