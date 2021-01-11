package com.matthewjohnson42.personalMemexService.data;

import java.util.Optional;

public interface Repository<T extends Entity, ID> {

    public T save(T e);

    public Optional<T> findById(ID id);

    public void deleteById(ID id);

}
