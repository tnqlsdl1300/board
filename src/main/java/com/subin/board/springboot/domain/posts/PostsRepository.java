package com.subin.board.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/*
- Repository
    - Entity 클래스와 Repository는 한 쌍 → 반드시 같은 폴더에 위치해야 함
    - domain 패키지에서 함께 관리
    - 인터페이스에 JpaRepository를 상속 받는 형식(형식: JpaRepository<Entity 명, PK 타입>)
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
