package com.subin.board.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    @RestController 란?
    - 컨트롤러를 기본적으로 JSON으로 반환해주게 만들어줌
    - MVC 패턴에서 각 메서드마다 사용했던 @ResponseBody 를 한번해 처리해준다고 보면 됨
*/
@RestController
public class HelloController {

    // @GetMapping("/hello") => 기존의 @RequestMapping(method = RequestMethod.GET) 을 대체
    @GetMapping("/hello")
    public String Hello(){
        return "hello";
    }
}