package com.matthewjohnson42.memexService.data;

import java.util.Optional;

/**
 * Implementation of a generic Repository, spanning Mongo and ES data stores
 * @param <T> the entity type being stored
 * @param <ID> the id type of the entity type specified by <T>
 *
 * @see Entity
 * @see DtoForEntity
 * @see DtoEntityConverter
 * @see DataService
 */
public interface Repository<T extends Entity, ID> {

    public T save(T e);

    public Optional<T> findById(ID id);

    public void deleteById(ID id);

}
