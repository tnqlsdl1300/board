package com.subin.board.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
- 세션값 가져오기 어노테이션 처리
    - 세션값 가져오기와 같은 반복되는 코드를 어노테이션으로 구현해서 유지보수성 높이기
    - @Target(ElementType.PARAMETER)
        - 이 어노테이션이 생성될 수 있는 위치를 지정
        - PARAMETER로 지정했으니 메서드의 파라미터로 선언된 객체에서만 사용할 수 있음
        - 이 외에도 클래스 선언문에 쓸 수 있는 TYPE등이 있음
    - @Retention(RetentionPolicy.RUNTIME)
        - 어느시점까지 어노테이션의 메모리를 가져갈 지 설정
        - RUNTIME → 어노테이션 런타임시에까지 사용할 수 있음. JVM이 자바 바이트코드가 담긴 class 파일에서 런타임 환경을 구성하고 런타임을 종료할 때까지 메모리는 살아있음
    - @interface
        - 이 파일을 어노테이션 클래스로 지정
        - LoginUser라는 이름을 가진 어노테이션이 생성되었다고 보면 됨
     */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

}
