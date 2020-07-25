package com.matthewjohnson42.personalMemexService.data.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A simple raw text entity, nothing more than the text data.
 * Used to specify the structure of the Mongo document.
 */
@Document(collection="rawText")
public class RawTextMongo {

    @Id
    private String id;
    private String text;

    public RawTextMongo() { }

    public RawTextMongo(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public RawTextMongo setId(String id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public RawTextMongo setText(String text) {
        this.text = text;
        return this;
    }

}
