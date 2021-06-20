package com.subin.board.springboot.web;

import com.subin.board.springboot.config.auth.LoginUser;
import com.subin.board.springboot.config.auth.dto.SessionUser;
import com.subin.board.springboot.domain.posts.Posts;
import com.subin.board.springboot.service.pagingPosts.PagingPostsService;
import com.subin.board.springboot.service.posts.PostsService;
import com.subin.board.springboot.utils.Criteria;
import com.subin.board.springboot.utils.JpaPageMaker;
import com.subin.board.springboot.utils.PageMaker;
import com.subin.board.springboot.web.dto.PagingPostsDto;
import com.subin.board.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    private final PagingPostsService pagingPostsService;
    private final HttpSession httpSession;
    
    // (JPA 페이징 없는) 메인화면으로 이동
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){

        /*
        - 기존에 SessionUser user = (SessionUser) httpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선됨
        - 이제는 어느 컨트롤러든지 @LoginUser를 매개변수로 받으면 세션 정보를 가져올 수 있게 됨
         */
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null){
            model.addAttribute("personName", user.getName());
        }

        return "index";
    }

    // 메인화면1으로 이동
    @GetMapping("/index1")
    public String index1(Model model, @LoginUser SessionUser user
            , @PageableDefault(size=5, sort="id", direction = Sort.Direction.DESC)Pageable pageable){

        /*
        - 기존에 SessionUser user = (SessionUser) httpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선됨
        - 이제는 어느 컨트롤러든지 @LoginUser를 매개변수로 받으면 세션 정보를 가져올 수 있게 됨
         */
        
        // 페이징 처리한 게시글 리스트
        Page<Posts> list = postsService.findAll(pageable);

        // 페이지 버튼 생성을 위한 객체
        JpaPageMaker pageMaker = new JpaPageMaker(pageable.getPageSize(), pageable.getPageNumber()+1);
        pageMaker.setTotalCount((int) list.getTotalElements());

        model.addAttribute("posts", list);
        model.addAttribute("pageMaker", pageMaker);

        if (user != null){
            model.addAttribute("personName", user.getName());
        }

        return "index1";
    }

    // 페이징 게시판으로 이동
    @GetMapping("/paging")
    public String paging(Model model, @LoginUser SessionUser user, Criteria cri) {

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(pagingPostsService.countTotalPosts());

        List<PagingPostsDto> list =  pagingPostsService.findAll(cri);

        model.addAttribute("list", list);
        model.addAttribute("pageMaker", pageMaker);

        return "paging";
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
