package com.matthewjohnson42.personalMemexService.data.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A simple raw text DTO, nothing more than just the text data
 */
public class RawTextDto {

    private String id;
    private String textContent;

    @JsonCreator
    public RawTextDto(@JsonProperty(value="textContent", required=true) String textContent) {
        this.textContent = textContent;
    }

    public RawTextDto() { }

    public RawTextDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public RawTextDto setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

}
