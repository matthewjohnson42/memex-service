package com.matthewjohnson42.personalMemexService.data.mongo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Class used to specify the structure of the Mongo document for the raw text entity.
 * Raw text entity is only text and DB tracking information.
 */
@Document(collection="rawText")
public class RawTextMongo {

    @Id
    private String id;
    private String textContent;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    public RawTextMongo() { }

    public String getId() {
        return id;
    }

    public RawTextMongo setId(String id) {
        this.id = id;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public RawTextMongo setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public RawTextMongo setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public RawTextMongo setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }

}
