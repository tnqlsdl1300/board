package com.subin.board.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
- JPA Auditing 이란?
    - 상속을 통해 생성일/수정일을 자동화하기 위해 사용
    - 수동으로 생성시간, 수정시간 컬럼을 각 entitiy 클래스들에 추가하지 않고 Auditing이 자동으로 생성해주게 함
    - 날짜타입은 웬만하면 LocalDateTime을 사용
    - @MappedSuperclass
        - JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식하도록 함
    - @EntityListeners(AuditingEntityListener.class)
        - BaseTimeEntity 클래스에 Auditing 기능을 포함시킴
    - @CreatedDate
        - Entity가 생성되어 저장될 때 시간이 자동으로 저장 됨
    - @LastModifiedDate
        - 조회한 Entity의 값을 변경할 때 시간이 자동으로 저장 됨
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
