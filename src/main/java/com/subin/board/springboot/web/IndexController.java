package com.subin.board.springboot.web;

import com.subin.board.springboot.config.auth.dto.SessionUser;
import com.subin.board.springboot.service.posts.PostsService;
import com.subin.board.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

// 페이지 이동을 위한 컨트롤러
@RequiredArgsConstructor
@Controller
public class IndexController {

    /*
    - Mustache 란?
        - 스프링 부트에서 공식 지원하는 템플릿 엔진
        - 설치법
            - intelij plugin에서 mustache 플러그인 설치
            - build.gradle에 의존성 추가
        - 파일 위치는 기본적으로 src/main/resources/templates
        - 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정
            - 앞: src/main/resources/templates, 뒤: .mustache
     */

    private final PostsService postsService;
    private final HttpSession httpSession;
    
    // 메인화면으로 이동
    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("posts", postsService.findAllDesc());

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null){
            model.addAttribute("personName", user.getName());
        }

        return "index";
    }

    // 게시글 등록 화면으로 이동
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    // 게시글 수정 화면으로 이동
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable long id, Model model){

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
