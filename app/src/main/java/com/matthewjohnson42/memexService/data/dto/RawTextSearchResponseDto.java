package com.matthewjohnson42.memexService.data.dto;

import java.util.List;

public class RawTextSearchResponseDto extends RawTextDto {

    private List<String> highlights;

    public RawTextSearchResponseDto() {}

    public RawTextSearchResponseDto(RawTextSearchResponseDto rawTextSearchResponseDto) {
        super(rawTextSearchResponseDto);
    }

    public List<String> getHighlights() {
        return highlights;
    }
    public RawTextSearchResponseDto setHighlights(List<String> highlights) {
        this.highlights = highlights;
        return this;
    }

}
