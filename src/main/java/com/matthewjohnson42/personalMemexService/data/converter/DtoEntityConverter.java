package com.matthewjohnson42.personalMemexService.data.converter;

public interface DtoEntityConverter<D, E> {

    public E convertDto(D d);

    public D convertEntity(E e);

    public E updateFromDto(E e, D d);

    public D updateFromEntity(D d, E e);

}
