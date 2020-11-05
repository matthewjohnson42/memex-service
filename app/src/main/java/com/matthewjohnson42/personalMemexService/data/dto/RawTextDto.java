package com.matthewjohnson42.personalMemexService.data.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * A simple raw text DTO, nothing more than just the text data
 */
public class RawTextDto {

    @JsonIgnore // along with getter and setter annotations, do not deserialize value
    private String id;
    private String textContent;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createDateTime;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime updateDateTime;

    @JsonCreator
    public RawTextDto(@JsonProperty(value="textContent", required=true) String textContent) {
        this.textContent = textContent;
    }

    public RawTextDto() { }

    @JsonIgnore
    public RawTextDto setId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty
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

    public RawTextDto setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public RawTextDto setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

}
