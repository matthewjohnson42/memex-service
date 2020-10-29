package com.matthewjohnson42.personalMemexService.data.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName="rawtext")
public class RawTextES {

    @Id
    @Field(type=FieldType.Keyword)
    private String id;
    @Field(type=FieldType.Text, analyzer="simple")
    private String textContent;
    @Field(type=FieldType.Date, format=DateFormat.basic_date_time)
    private LocalDateTime createDateTime;
    @Field(type=FieldType.Date, format=DateFormat.basic_date_time)
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

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public RawTextES setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public RawTextES setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }

}
