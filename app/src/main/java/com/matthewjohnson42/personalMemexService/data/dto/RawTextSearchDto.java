package com.matthewjohnson42.personalMemexService.data.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class RawTextSearchDto {

    private String searchString;
    private int pageSize;
    private int pageNumber;
    private LocalDateTime startCreateDate;
    private LocalDateTime endCreateDate;
    private LocalDateTime startUpdateDate;
    private LocalDateTime endUpdateDate;

    @JsonCreator
    RawTextSearchDto(
            @JsonProperty(value="searchString", required=true) String searchString,
            @JsonProperty(value="pageSize", required=true) int pageSize,
            @JsonProperty(value="pageNumber", required=true) int pageNumber,
            @JsonProperty(value="startCreateDate", required=false) LocalDateTime startCreateDate,
            @JsonProperty(value="endCreateDate", required=false) LocalDateTime endCreateDate,
            @JsonProperty(value="startUpdateDate", required=false) LocalDateTime startUpdateDate,
            @JsonProperty(value="endUpdateDate", required=false) LocalDateTime endUpdateDate) {
        this.searchString = searchString;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        if (startCreateDate != null) {
            this.startCreateDate = startCreateDate;
        }
        if (endCreateDate != null) {
            this.endCreateDate = endCreateDate;
        }
        if (startUpdateDate != null) {
            this.startUpdateDate = startUpdateDate;
        }
        if (endUpdateDate != null) {
            this.endUpdateDate = endUpdateDate;
        }
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

    public LocalDateTime getStartCreateDate() {
        return startCreateDate;
    }

    public RawTextSearchDto setStartCreateDate(LocalDateTime startCreateDate) {
        this.startCreateDate = startCreateDate;
        return this;
    }

    public LocalDateTime getEndCreateDate() {
        return endCreateDate;
    }

    public RawTextSearchDto setEndCreateDate(LocalDateTime endCreateDate) {
        this.endCreateDate = endCreateDate;
        return this;
    }

    public LocalDateTime getStartUpdateDate() {
        return startUpdateDate;
    }

    public RawTextSearchDto setStartUpdateDate(LocalDateTime startUpdateDate) {
        this.startUpdateDate = startUpdateDate;
        return this;
    }

    public LocalDateTime getEndUpdateDate() {
        return endUpdateDate;
    }

    public RawTextSearchDto setEndUpdateDate(LocalDateTime endUpdateDate) {
        this.endUpdateDate = endUpdateDate;
        return this;
    }

}
