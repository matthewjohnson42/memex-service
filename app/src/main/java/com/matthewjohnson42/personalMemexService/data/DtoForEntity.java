package com.matthewjohnson42.personalMemexService.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * Describes DTOs associated with an {@link Entity}
 * Returned from {@link DataService} extensions
 *
 * @param <ID> the class of the id
 *
 * @see Entity
 * @see DataService
 * @see Repository
 * @see DtoEntityConverter
 */
public abstract class DtoForEntity<ID> {

    @JsonIgnore
    private LocalDateTime createDateTime;
    @JsonIgnore
    private LocalDateTime updateDateTime;

    public DtoForEntity() { }

    public DtoForEntity(DtoForEntity<ID> dto) {
        this.createDateTime = dto.getCreateDateTime();
        this.updateDateTime = dto.getUpdateDateTime();
    }

    public abstract ID getId();

    public abstract DtoForEntity<ID> setId(ID id);

    @JsonIgnore
    public DtoForEntity setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }

    @JsonProperty
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    @JsonIgnore
    public DtoForEntity setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }

    @JsonProperty
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

}
