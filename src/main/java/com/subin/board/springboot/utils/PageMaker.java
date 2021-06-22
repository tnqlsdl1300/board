package com.subin.board.springboot.utils;

import lombok.Getter;
import lombok.Setter;

// 페이징을 위한 버튼들을 만드는 클래스

@Getter
@Setter
public class PageMaker {

    private Criteria cri;
    private int totalCount;         // 총 게시글 수
    private int startPage;          // 화면에 보여질 첫번째 페이지 번호, 시작 페이지 번호
    private int endPage;            // 화면에 보여질 마지막 페이지 번호, 끝 페이지 번호
    private int tempEndPage;        // 맨끝 페이지 번호
    private boolean prev;           // 이전 버튼 생성 여부
    private boolean next;           // 다음 버튼 생성 여부
    private int displayPageNum = 5; // 화면 하단에 보여지는 페이지 버튼의 수

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {

        // cri.getPage() : 현재 페이지 번호
        // cri.getPerPageNum() : 한 페이지당 보여줄 게시글의 갯수
        // 끝 페이지 번호 = (현재 페이지 번호 / 화면에 보여질 페이지 번호의 갯수) * 화면에 보여질 페이지 번호의 갯수
        endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);

        // 시작 페이지 번호 = (끝 페이지 번호 - 화면에 보여질 페이지 번호의 갯수) + 1
        startPage = (endPage - displayPageNum) + 1;
        if(startPage <= 0) startPage = 1;

        // 마지막 페이지 번호 = 총 게시글 수 / 한 페이지당 보여줄 게시글의 갯수
        tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        // 이전 버튼 생성 여부 = 시작 페이지 번호 == 1 ? false : true
        prev = startPage == 1 ? false : true;
        // 다음 버튼 생성 여부 = 끝 페이지 번호 * 한 페이지당 보여줄 게시글의 갯수 < 총 게시글의 수 ? true: false
        next = endPage * cri.getPerPageNum() < totalCount ? true : false;
    }

    public boolean getPrev(){
        return prev;
    }

    public boolean getNext(){
        return next;
    }

}
