package com.matthewjohnson42.personalMemexService.data.service;

import com.matthewjohnson42.personalMemexService.data.converter.DtoEntityConverter;

public abstract class DataService<D, E> {

    protected DtoEntityConverter<D, E> converter;

}
