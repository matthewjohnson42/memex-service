package com.matthewjohnson42.memex.data.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Stub implementation of RawTextDto to resolve compilation issues.
 * This class provides the minimal API needed for the local DTOs and services that use it.
 */
public class RawTextDto extends DtoForEntity<String> {
    
    private String id;
    private String title;
    private String text;
    private String textContent;
    private List<String> tags;

    public RawTextDto() {
        this.tags = new ArrayList<>();
    }

    public RawTextDto(RawTextDto other) {
        super(other);
        if (other != null) {
            this.id = other.getId();
            this.title = other.getTitle();
            this.text = other.getText();
            this.textContent = other.getTextContent();
            this.tags = other.getTags() != null ? new ArrayList<>(other.getTags()) : new ArrayList<>();
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
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

    public String getTextContent() {
        return textContent;
    }

    public RawTextDto setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public RawTextDto setTags(List<String> tags) {
        this.tags = tags != null ? tags : new ArrayList<>();
        return this;
    }

    // Convenience methods for compatibility with test expectations
    public LocalDateTime getCreateDate() {
        return getCreateDateTime();
    }

    public LocalDateTime getUpdateDate() {
        return getUpdateDateTime();
    }
}