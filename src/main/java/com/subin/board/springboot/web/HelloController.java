package com.subin.board.springboot.web;

import com.subin.board.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /*
    @RequestParam() 이란?
        - 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
        - [@RequestParam("name")] 이라는 이름으로 넘긴 값을 [String name] 에 넣어준다
     */
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){

        return new HelloResponseDto(name, amount);
    }
}