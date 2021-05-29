package com.subin.board.springboot.web;

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

    // 게시글 등록
    @PostMapping("/api/v1/posts")
    public long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    // 게시글 수정
    @PutMapping("/api/v1/posts/{id}")
    public long update(@PathVariable long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }
    
    // 게시글 조회
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable long id){
        return postsService.findById(id);
    }

    // 게시글 삭제
    @DeleteMapping("/api/v1/posts/{id}")
    public void deleteById(@PathVariable long id){
        postsService.deleteById(id);
    }

}
