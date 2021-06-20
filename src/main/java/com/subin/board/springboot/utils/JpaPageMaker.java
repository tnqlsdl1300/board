package com.subin.board.springboot.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JpaPageMaker {

    private int thisPage;
    private int perPageNum;         // 한 페이지당 보여줄 게시글의 갯수
    private int totalCount;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int displayPageNum = 2; // 화면에 보여질 페이지 번호의 갯수

    public JpaPageMaker(int perPageNum, int thisPage){
        this.perPageNum = perPageNum;
        this.thisPage = thisPage;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {

        endPage = (int) (Math.ceil(thisPage / (double) displayPageNum) * displayPageNum);

        // 시작 페이지 번호 = (끝 페이지 번호 - 화면에 보여질 페이지 번호의 갯수) + 1
        startPage = (endPage - displayPageNum) + 1;
        if(startPage <= 0) startPage = 1;

        // 마지막 페이지 번호 = 총 게시글 수 / 한 페이지당 보여줄 게시글의 갯수
        int tempEndPage = (int) (Math.ceil(totalCount / (double) perPageNum));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        // 이전 버튼 생성 여부 = 시작 페이지 번호 == 1 ? false : true
        prev = startPage == 1 ? false : true;
        // 다음 버튼 생성 여부 = 끝 페이지 번호 * 한 페이지당 보여줄 게시글의 갯수 < 총 게시글의 수 ? true: false
        next = endPage * perPageNum < totalCount ? true : false;

    }

}
