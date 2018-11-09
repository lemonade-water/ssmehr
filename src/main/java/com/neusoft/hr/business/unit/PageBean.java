package com.neusoft.hr.business.unit;

import java.util.List;

public class PageBean<T> {
    private int page; //当前页数
    private int totalCount;  //总记录数
    private int totalPage;  //总页数
    private int limit;   //每页显示的记录数
    private List<T> list; //每页显示数据记录的集合；
    private String query;
    public PageBean() {
        this.page=1;
        this.limit=5;
    }

    public PageBean(int page, int totalCount, int totalPage, int limit, List<T> list,String query) {
        this.page = page;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.limit = limit;
        this.list = list;
        this.query=query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return (totalCount%limit)==0?(totalCount/limit):(totalCount/limit+1);
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
