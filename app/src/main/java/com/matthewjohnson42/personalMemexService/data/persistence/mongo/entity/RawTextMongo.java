package com.matthewjohnson42.personalMemexService.data.persistence.mongo.entity;

import com.matthewjohnson42.personalMemexService.data.entity.AbstractRawTextEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A simple raw text entity, nothing more than the text data.
 * Used to specify the structure of the Mongo document.
 */
@Document(collection="rawText")
public class RawTextMongo extends AbstractRawTextEntity {

    @Id
    private String id;

    public RawTextMongo() { }

}
