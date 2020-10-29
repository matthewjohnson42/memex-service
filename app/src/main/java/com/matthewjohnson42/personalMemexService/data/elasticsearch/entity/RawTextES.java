package com.matthewjohnson42.personalMemexService.data.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Class used to represent the raw text entity as an ElasticSearch entity.
 * Raw text entity is only text and DB tracking data.
 */
public class RawTextES {

    private String id;
    private String textContent;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    public RawTextES () { }

    public String getId() {
        return id;
    }

    public RawTextES setId(String id) {
        this.id = id;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public RawTextES setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public RawTextES setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public RawTextES setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }

}
