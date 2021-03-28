package com.matthewjohnson42.personalMemexService.data.dto;

public class RawTextSearchResponseDto extends RawTextDto {

    private String gloss;

    public RawTextSearchResponseDto setGloss(String gloss) {
        this.gloss = gloss;
        return this;
    }

    public String getGloss() {
        return gloss;
    }
}
