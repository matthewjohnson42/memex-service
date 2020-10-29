package com.matthewjohnson42.personalMemexService.data.entity;

import java.time.LocalDateTime;

/**
 * A class describing the Raw Text entity
 * The entity is used to describe an unannotated text document
 */
public abstract class AbstractRawTextEntity {

    protected String id;
    protected String textContent;
    protected LocalDateTime createDateTime;
    protected LocalDateTime updateDateTime;

    public String getId() {
        return id;
    }

    public AbstractRawTextEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public AbstractRawTextEntity setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public AbstractRawTextEntity setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public AbstractRawTextEntity setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }

}
