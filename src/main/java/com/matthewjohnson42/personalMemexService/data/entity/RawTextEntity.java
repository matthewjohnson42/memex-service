package com.matthewjohnson42.personalMemexService.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A simple raw text entity, nothing more than the text data.
 * Used to specify the structure of the Mongo document.
 */
@Document(collection="rawText")
public class RawTextEntity {

    @Id
    public String id;
    public String text;

    public RawTextEntity() { }

    public RawTextEntity(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public RawTextEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public RawTextEntity setText(String text) {
        this.text = text;
        return this;
    }

}
