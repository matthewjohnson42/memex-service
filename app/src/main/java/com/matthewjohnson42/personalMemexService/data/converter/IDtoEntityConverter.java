package com.matthewjohnson42.personalMemexService.data.converter;

/**
 * Interface describing a converter of data transfer objects and entities for an arbitrary persistence provider
 * @param <D> the DTO class
 * @param <E> the Entity class
 */
public interface IDtoEntityConverter<D, E> {

    public E convertDto(D d);

    public D convertEntity(E e);

    public E updateFromDto(E e, D d);

    public D updateFromEntity(D d, E e);

}
