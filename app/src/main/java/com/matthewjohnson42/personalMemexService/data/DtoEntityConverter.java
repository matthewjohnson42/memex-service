package com.matthewjohnson42.personalMemexService.data;

public interface DtoEntityConverter<ID, D extends DtoForEntity<ID>, E extends Entity<ID>> {

    public E convertDto(D d);

    public D convertEntity(E e);

    default public E updateFromDto(E e, D d) {
        if (d.getId() != null) {
            e.setId(d.getId());
        }
        return e;
    };

    default public D updateFromEntity(D d, E e) {
        if (e.getId() != null) {
            d.setId(e.getId());
        }
        if (e.getCreateDateTime() != null) {
            d.setCreateDateTime(e.getCreateDateTime());
        }
        if (e.getUpdateDateTime() != null) {
            d.setUpdateDateTime(e.getUpdateDateTime());
        }
        return d;
    };

}
