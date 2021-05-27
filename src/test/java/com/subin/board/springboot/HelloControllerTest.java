package com.subin.board.springboot;

import com.subin.board.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

        /*
            - mvc.perform(get("/hello"))
                - MockMvc를 통해 /hello 주소로 HTTP GET 요청
                - 아마 테스트 코드 말고 기존 Controller의 /hello 를 실행하는 것 같음
                - 체이닝 지원 ⇒ asfasf.asfa.asfaf ... 와 같은 형식
            - .andExpect(status().isOk())
                - mvc.perform 의 값을 검증
                - status() ⇒ HTTP Header의 Status 값 검증 (200, 404, 500 등등)
                - .isOk() ⇒ Status 값이 200인지 아닌지 검증
            - .andExpect(content().string(hello));
                - mvc.perform 의 값을 검증
                - content().string(hello)) ⇒ 응답 본문의 내용을 검증
                - 실제 Controller 에서 "hello"를 리턴하기 때문에 테스트코드에서 "hello"를 리턴하는지 확인.만약 값이 다를시 오류처리 ⇒ 실제 Controller와 test 코드의 return  값이 같아야 함
         */
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "test";
        int amount = 1000;

        /*
        param()
            - API 테스트할 때 사용될 요창 파라미터를 설정
            - 값은 String 만 허용
            - 숫자 / 날짜 등의 데이터도 등록할때는 문자열로 변경해야 함

        jsonPath()
            - JSON 응답값을 필드별로 검증할 수 있는 메서드
            - $를 기준으로 필드명을 명시
            - 여기서는 name과 amount를 명시하니 $.name, $.amount로 검증
         */
        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.name", is(name)))
                            .andExpect(jsonPath("$.amount", is(amount)));
    }

}
