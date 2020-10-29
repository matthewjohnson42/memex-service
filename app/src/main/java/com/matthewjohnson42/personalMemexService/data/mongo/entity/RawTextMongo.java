package com.matthewjohnson42.personalMemexService.data.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * A simple raw text entity, nothing more than the text data.
 * Used to specify the structure of the Mongo document.
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

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public RawTextMongo setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public RawTextMongo setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }

}
