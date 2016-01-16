package com.horadrim.talrasha.common.response.json;

import com.horadrim.talrasha.common.CodeDefined;

import java.util.List;

/**
 * Created by wh on 2014/5/28.
 */
public class PageJsonRes<T> extends AbstractJsonRes {

    private int totalPage;
    private int currentPage;
    private List<T> data;

    public PageJsonRes() {
        super(CodeDefined.SUCCESS, CodeDefined.getMessage(CodeDefined.SUCCESS));
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
