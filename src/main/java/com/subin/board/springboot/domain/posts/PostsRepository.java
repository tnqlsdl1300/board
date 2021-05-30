package com.subin.board.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
- Repository
    - Entity 클래스와 Repository는 한 쌍 → 반드시 같은 폴더에 위치해야 함
    - domain 패키지에서 함께 관리
    - 인터페이스에 JpaRepository를 상속 받는 형식(형식: JpaRepository<Entity 명, PK 타입>)
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    /*
    - JPQL 이란?
        - JPA를 구현한 프레임워크에서 사용하는 언어
        - p는 p FROM Posts p(p FROM Posts as p) 테이블의 별칭을 뜻함
        - 대소문자 구분 지켜줘야함!!!!!!
            - 명령어의 경우 대문자
            - 테이블명의 첫문자만 대문자
     */
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
