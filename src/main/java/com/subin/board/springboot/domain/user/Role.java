package com.subin.board.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    // 권한코드 끝마다 컴마(,)임!! 끝날때만 세미콜론(;)
    // 권한코드에 항상 ROLE_이 앞에 있어야만 함
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

}

