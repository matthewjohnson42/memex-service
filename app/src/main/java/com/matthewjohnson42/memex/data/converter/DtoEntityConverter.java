package com.matthewjohnson42.memex.data.converter;

// Stub interface for testing
public interface DtoEntityConverter<ID, D, E> {
    D convertEntity(E entity);
    E convertDto(D dto);
}