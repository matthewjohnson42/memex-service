package com.matthewjohnson42.memexService.data.dto;

public class PageRequestDto {

    private int pageSize;
    private int pageNumber;
    private String sort;

    PageRequestDto() { }

    public int getPageSize() {
        return pageSize;
    }

    public PageRequestDto setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public PageRequestDto setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public String getSort() {
        return sort;
    }

    public PageRequestDto setSort(String sort) {
        this.sort = sort;
        return this;
    }

}
