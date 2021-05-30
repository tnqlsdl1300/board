package com.subin.board.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩(){
        // index 페이지를 String으로 받아와서 body에 저장
        String body = this.restTemplate.getForObject("/", String.class);

        // body에 "스프링 부트로 시작하는 웹 서비스"이 포함되어 있는지 체크
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");

    }

}
