package com.matthewjohnson42.personalMemexService.data.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

/**
 * A simple raw text DTO, nothing more than just the text data
 */
public class RawTextDTO {

    String id;
    @NotNull
    String text;

    @JsonCreator
    public RawTextDTO(@JsonProperty(value="text", required=true) String text) {
        this.text = text;
    }

    public RawTextDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public RawTextDTO setText(String text) {
        this.text = text;
        return this;
    }

    public String getText() {
        return text;
    }

}
