package com.matthewjohnson42.personalMemexService.data.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

public class PageRequestDto {

    private int pageSize;
    private int pageNumber;
    private String sort;

    @JsonCreator
    PageRequestDto(
            @JsonProperty(value="pageSize", required=true) int pageSize,
            @JsonProperty(value="pageNumber", required=true) int pageNumber,
            @JsonProperty(value="sort", required=false) String sort) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sort = StringUtils.isEmpty(sort) ? "desc" : sort;
    }

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
