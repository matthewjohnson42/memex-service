package com.matthewjohnson42.personalMemexService.data.dto;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * A list of Mongo ids
 */
public class IdListDTO {

    @NotBlank
    List<String> ids;

    public IdListDTO() {
        ids = new ArrayList<>();
    }

    public IdListDTO(List<String> ids) {
        this.ids = ids;
    }

    public IdListDTO setIds(List<String> ids) {
        this.ids = ids;
        return this;
    }

    public List<String> getIds() {
        return ids;
    }

    public IdListDTO addId(String id) {
        ids.add(id);
        return this;
    }

}
