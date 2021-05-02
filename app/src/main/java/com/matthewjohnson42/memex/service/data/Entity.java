package com.matthewjohnson42.memex.service.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Entities associated with the {@link Repository} object
 *
 * @see Repository
 * @see DtoForEntity
 * @see DataService
 * @see DtoEntityConverter
 */
public abstract class Entity<ID> {

    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    public abstract ID getId();

    public abstract Entity<ID> setId(ID id);

    public Entity() { }

    public Entity(Entity<ID> entity) {
        this.createDateTime = entity.getCreateDateTime();
        this.updateDateTime = entity.getUpdateDateTime();
    }

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
