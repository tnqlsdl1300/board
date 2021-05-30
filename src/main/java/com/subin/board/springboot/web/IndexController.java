package com.subin.board.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    @GetMapping("/")
    public String index(){
        return "index";
    }

}
