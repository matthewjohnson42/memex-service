package com.matthewjohnson42.personalMemexService.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Entities from any repository type referenced by the application
 */
public abstract class Entity<ID> {

    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    public abstract ID getId();

    public abstract Entity<ID> setId(ID id);

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public Entity setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public Entity setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }

}
