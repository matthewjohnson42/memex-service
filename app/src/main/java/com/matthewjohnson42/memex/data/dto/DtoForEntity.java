package com.matthewjohnson42.memex.data.dto;

import java.time.LocalDateTime;

/**
 * Stub implementation of DtoForEntity to resolve compilation issues.
 * This class provides the minimal API needed for the local DTOs that extend it.
 */
public abstract class DtoForEntity<ID> {
    
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    public DtoForEntity() {
        // Default constructor
    }

    public DtoForEntity(DtoForEntity<ID> other) {
        if (other != null) {
            this.createDateTime = other.getCreateDateTime();
            this.updateDateTime = other.getUpdateDateTime();
        }
    }

    public abstract ID getId();
    public abstract DtoForEntity<ID> setId(ID id);

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public DtoForEntity<ID> setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public DtoForEntity<ID> setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }
}