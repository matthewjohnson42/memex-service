package com.matthewjohnson42.memex.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

// Stub class to make compilation work
public class RawTextDto extends DtoForEntity<String> {
    protected String id;
    protected String title;
    protected String text;
    protected Set<String> tags;
    protected LocalDateTime createDate;
    protected LocalDateTime updateDate;
    
    public RawTextDto() {
        this.tags = new HashSet<>();
    }
    
    public RawTextDto(RawTextDto other) {
        super(other);
        this.tags = new HashSet<>(); // Always initialize tags
        if (other != null) {
            this.id = other.getId();
            this.title = other.getTitle();
            this.text = other.getText();
            this.tags = other.getTags();
            this.createDate = other.getCreateDate();
            this.updateDate = other.getUpdateDate();
        }
    }
    
    @Override
    @JsonIgnore
    public String getId() {
        return id;
    }
    
    @Override
    @JsonIgnore
    public RawTextDto setId(String id) {
        this.id = id;
        return this;
    }
    
    public String getTitle() {
        return title;
    }
    
    public RawTextDto setTitle(String title) {
        this.title = title;
        return this;
    }
    
    public String getText() {
        return text;
    }
    
    public RawTextDto setText(String text) {
        this.text = text;
        return this;
    }
    
    public Set<String> getTags() {
        return tags;
    }
    
    public RawTextDto setTags(Set<String> tags) {
        this.tags = tags;
        return this;
    }
    
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    
    public RawTextDto setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
        return this;
    }
    
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
    
    public RawTextDto setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
        return this;
    }
}