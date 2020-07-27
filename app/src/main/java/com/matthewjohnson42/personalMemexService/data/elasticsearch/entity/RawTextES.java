package com.matthewjohnson42.personalMemexService.data.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName="rawtext")
public class RawTextES {

    @Id
    @Field(type=FieldType.Keyword)
    private String id;
    @Field(type=FieldType.Text, analyzer="simple")
    private String textContent;

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

}
