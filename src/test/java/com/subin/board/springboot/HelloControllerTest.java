package com.subin.board.springboot;

import com.subin.board.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
    @RunWith(SpringRunner.class)
    - 테스트를 진행할 때 JUnit에 내장된 실행자 외에 괄호안의 실행자를 실행시킴 -> SpringRunner
    - 스프링부트 테스트와 JUnit 사이의 연결자 역할을 함

    @WebMvcTest(controllers = HelloController.class)
    - Web(Spring MVC)에 집중할 수 있는 어노테이션
    - 선언할 경우 @Controller, @ControllerAdvice 등을 사용 가능
    - 대신, @Service, @Component, @Repository 등은 사용 불가
*/
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    /*
        MockMvc 란?
        - 웹 API를 테스트할 때 사용
        - 스프링 MVC 테스트의 시작점
        - 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있음
    */
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

}
