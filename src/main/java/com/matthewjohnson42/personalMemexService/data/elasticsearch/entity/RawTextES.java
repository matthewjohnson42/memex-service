package com.matthewjohnson42.personalMemexService.data.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName="rawtext")
public class RawTextES {

    @Id
    private String id; // set up to be validated
    private String text;

    public RawTextES () { }

    public String getId() {
        return id;
    }

    public RawTextES setId(String id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public RawTextES setText(String text) {
        this.text = text;
        return this;
    }

}
