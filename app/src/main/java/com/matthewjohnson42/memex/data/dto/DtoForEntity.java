package com.matthewjohnson42.memex.data.dto;

// Stub class to make compilation work
public abstract class DtoForEntity<T> {
    protected T id;
    
    public DtoForEntity() {}
    
    public DtoForEntity(DtoForEntity<T> other) {
        if (other != null) {
            this.id = other.getId();
        }
    }
    
    public abstract T getId();
    public abstract DtoForEntity<T> setId(T id);
}