package com.matthewjohnson42.personalMemexService.data.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A simple raw text DTO, nothing more than just the text data
 */
public class RawTextDto {

    private String id;
    private String text;

    @JsonCreator
    public RawTextDto(@JsonProperty(value="text", required=true) String text) {
        this.text = text;
    }

    public RawTextDto() { }

    public RawTextDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public RawTextDto setText(String text) {
        this.text = text;
        return this;
    }

    public String getText() {
        return text;
    }

}
