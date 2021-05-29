package com.subin.board.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
    @EnableJpaAuditing 이란?
        - JPA Auditing 활성화를 위한 어노테이션

    @SpringBootApplication 란?
        - 프로젝트의 메인 클래스
        - 내장 WAS를 실행하기 때문에 Tomcat이 필요 없어지고 스프링부트로 만들어진 Jar 파일을 실행함
        
        * 주의: 반드시 프로젝트의 최상단에 위치해 있어야 함
*/
@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
