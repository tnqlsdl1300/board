package com.subin.board.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
- @NoArgsConstructor
    - 기본 생성자 자동 추가
    - public Posts(){}와 같은 효과

- @Entity
    - 테이블과 링크될 클래스임을 나타냄
    - 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭
    - ex) SalesManager.java → sales_manager table
    - Entity 클래스에서는 절대 Setter 메서드를 만들면 안됨 → 직접적으로 값을 변경하는 것 금지 → 사실상 Entity 클래스가 테이블과 같기 때문에 매우 위험한 짓
    - 해결: 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메서드를 추가해야 해야 함
 */
@Getter
@NoArgsConstructor
@Entity
public class Posts {

    /*
    - @Id
        - 해당 테이블의 PK 필드를 나타냄
        - 웬만하면 Entity의 PK는 Long 타입의 Auto_increment를 추천함(주민등록번호, 복합키 등은 유니크 키로 별도로 추가하는 것을 추천)
            - FK를 맺을 때 다른 테이블에서 복합키를 전부 갖고 있거나, 중간 테이블을 하나 더 둬야 하는 상황이 발생
            - 인덱스에 좋은 영향을 끼치지 못함
            - 유니크한 조건이 변경될 경우 PK 전체를 수정해야 하는 일이 발생

    - @GeneratedValue
        - PK의 생성 규칙을 나타냄
        - strategy = GenerationType.IDENTITY를 써야 auto_increment(mysql의 시퀀스)가 적용됨
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*
    - @Column
        - 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨 → 생략 가능
        - 사용하는 이뉴는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
        - 문자열의 경우 varchar(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나 타입을 TEXT로 변경하고 싶은 경우에 사용됨
     */
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    /*
    - @Builder
        - 해당 클래스의 빌더 패턴 클래스를 생성
        - 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
        - 기존의 setter를 사용하지 않고 Builder()로 빠르게 값을 넣을 수 있음
     */
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
