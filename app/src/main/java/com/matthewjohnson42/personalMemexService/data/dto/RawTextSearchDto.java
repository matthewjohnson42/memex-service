package com.matthewjohnson42.personalMemexService.data.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RawTextSearchDto {

    private String searchString;
    private int pageSize;
    private int pageNumber;

    @JsonCreator
    RawTextSearchDto(
            @JsonProperty(value="searchString", required=true) String searchString,
            @JsonProperty(value="pageSize", required=true) int pageSize,
            @JsonProperty(value="pageNumber", required=true) int pageNumber) {
        this.searchString = searchString;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public String getSearchString() {
        return searchString;
    }

    public RawTextSearchDto setSearchString(String searchString) {
        this.searchString = searchString;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public RawTextSearchDto setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public RawTextSearchDto setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

}
