package com.matthewjohnson42.personalMemexService.data.persistence.elasticsearch.entity;

import com.matthewjohnson42.personalMemexService.data.entity.AbstractRawTextEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName="rawtext")
public class RawTextES extends AbstractRawTextEntity {

    @Id
    @Field(type=FieldType.Keyword)
    private String id;
    @Field(type=FieldType.Text, analyzer="simple")
    private String textContent;
    @Field(type=FieldType.Date)
    private LocalDateTime createDateTime;
    @Field(type=FieldType.Date)
    private LocalDateTime updateDateTime;

    public RawTextES () { }

}
