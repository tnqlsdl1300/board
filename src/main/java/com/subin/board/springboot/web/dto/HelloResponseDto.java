package com.subin.board.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
    Lombok 이란?
    - Getter, Setter, 기본생성자, toString 등을 어노테이션으로 자동 생성해줌
    - @Getter ⇒ 선언된 모든 필드의 get 메서드를 생성
    - @RequiredArgsConstructor
        - 선언된 모든 final 필드가 포함된 생성자를 생성
        - final이 없는 필드는 생성X
 */
@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
