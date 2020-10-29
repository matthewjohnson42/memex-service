package com.matthewjohnson42.personalMemexService.data.service;

import com.matthewjohnson42.personalMemexService.data.converter.IDtoEntityConverter;

public abstract class DataService<D, E> {

    protected IDtoEntityConverter<D, E> converter;

}
