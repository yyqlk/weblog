package com.like.weblog.weblog.dto;

import com.like.weblog.weblog.model.Notice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageNoticeDTO {
    private List<Notice> notices;
    private boolean isfist;
    private boolean isLast;
    private Integer currentPage; //当前页
    private Integer totalPage;
    private List<Integer> pages; //页数集合

    public void setNoticesPage(Integer page, Integer size, Integer totalCount){
        this.currentPage=page;
        if(totalCount%size != 0){
            this.totalPage=totalCount/size+1;
        }else{
            this.totalPage=totalCount/size;
        }
        if(currentPage == 1){
            this.isfist = true;
        }

        if(currentPage == totalPage){
            isLast =true;
        }

        //分页逻辑
        pages = new ArrayList<>();
        if(totalPage <=5 && currentPage<=3){
            for(int i =totalPage; i>0;i--){
                pages.add(i);
            }
            Collections.reverse(pages);
        }else if(currentPage<=3) {
            for(int i=1; i<=5;i++) {
                pages.add(i);
            }
        } else if(currentPage > 3 && currentPage + 2 <= totalPage) {
            for (int i = currentPage - 3; i < currentPage + 2; i++) {
                pages.add(i);
            }
        }else if (currentPage + 2 > totalPage) {
            for (int i = totalPage - 5; i < totalPage; i++) {
                pages.add(i);
            }
        }
    }

    public List<Notice> getNotices() {
        return notices;
    }

    public void setNotices(List<Notice> notices) {
        this.notices = notices;
    }

    public boolean isIsfist() {
        return isfist;
    }

    public void setIsfist(boolean isfist) {
        this.isfist = isfist;
    }

    public boolean isIsLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }
}
