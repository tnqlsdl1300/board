package com.subin.board.springboot.utils;

import lombok.Getter;

// 페이징 처리를 해줄 클래스

@Getter
public class Criteria {

    private int page;       // 현재 페이지 번호
    private int perPageNum; // 한 페이지당 보여줄 게시글의 개수

    // 특정 페이지의 게시글 시작 번호, 게시글 시작 행 번호
    public int getPageStart(){
        return (this.page-1)*perPageNum;
    }

    public Criteria(){
        this.page = 1;
        this.perPageNum = 10;
    }

    // 페이지가 음수값이 되지 않게 설정. 음수가 되면 1페이지를 나타냄
    public void setPage(int page) {
        if (page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    // 페이지당 보여줄 게시글 수가 변하지 않게 설정
    public void setPerPageNum(int perPageNum) {
        int cnt = this.perPageNum;
        if (perPageNum != cnt) {
            this.perPageNum = cnt;
        } else {
            this.perPageNum = perPageNum;
        }
    }



}
