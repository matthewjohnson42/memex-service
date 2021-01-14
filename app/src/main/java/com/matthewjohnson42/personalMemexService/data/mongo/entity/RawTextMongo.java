package com.matthewjohnson42.personalMemexService.data.mongo.entity;

import com.matthewjohnson42.personalMemexService.data.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class used to specify the structure of the Mongo document for the raw text entity.
 * Raw text entity is only text and DB tracking information.
 */
@Document(collection="rawText")
public class RawTextMongo extends Entity<String> {

    @Id
    private String id;
    private String textContent;

    public RawTextMongo() { }

    @Override
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

}
