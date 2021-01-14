package com.matthewjohnson42.personalMemexService.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Describes a data service used for implementing persistence logic and transforming an entity to a DTO
 * @param <ID> class of the ID used by both the DTO and the Entity
 * @param <D> class of the DTO, used for data transformations and serialization
 * @param <E> class of the Entity, used for data persistence
 */
public abstract class DataService<ID, D extends DtoForEntity<ID>, E extends Entity<ID>> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected DtoEntityConverter<ID, D, E> converter;
    protected Repository<E, ID> repository;

    public DataService(DtoEntityConverter<ID, D, E> converter, Repository<E, ID> repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public D getById(ID id) {
        E entity = getIfExists(id);
        return converter.convertEntity(entity);
    }

    // pass in date time to allow non-assignment in converter
    public D create(D dto, LocalDateTime createDateTime) {
        E entity = converter.convertDto(dto);
        if (entity.getId() == null) {
            String msg = String.format("No id found for entity of type %s", entity.getClass().toString());
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, msg);
        }
        entity.setCreateDateTime(createDateTime);
        entity.setUpdateDateTime(createDateTime);
        entity = repository.save(entity);
        logger.info(String.format("Wrote new %s object with id %s", entity.getClass(), entity.getId()));
        return converter.convertEntity(entity);
    }

    public D create(D dto) {
        return create(dto, LocalDateTime.now());
    }

    // pass in date time to allow non-assignment in converter
    public D update(D dto, LocalDateTime updateDateTime) {
        E entity = getIfExists(dto.getId());
        if (entity.getId() == null) {
            String msg = String.format("No id found for entity of type %s", entity.getClass().toString());
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, msg);
        }
        entity = converter.updateFromDto(entity, dto);
        entity.setUpdateDateTime(updateDateTime);
        entity = repository.save(entity);
        logger.info(String.format("Updated %s object with id %s", entity.getClass(), entity.getId()));
        return converter.convertEntity(entity);
    }

    public D update(D dto) {
        return create(dto, LocalDateTime.now());
    }

    public D deleteById(ID id) {
        E entity = getIfExists(id);
        repository.deleteById(id);
        logger.info(String.format("Deleted %s object with id %s", entity.getClass(), id));
        return converter.convertEntity(entity);
    }

    public D delete(D dto) {
        return deleteById(dto.getId());
    }

    public boolean exists(ID id) {
        return repository.findById(id).isPresent();
    }

    protected E getIfExists(ID id) {
        Optional<E> entity = repository.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            String msg = String.format("No entity found for id %s", id.toString());
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }
    }

}
