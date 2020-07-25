package com.matthewjohnson42.personalMemexService.data.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of Mongo ids
 * todo update to be a list of summary objects including tags and document date (rather than just document ids)
 */
public class IdListDto {

    private List<String> ids; // todo make similar to "javax.validation.constraints.notBlank"

    public IdListDto() {
        ids = new ArrayList<>();
    }

    public IdListDto(List<String> ids) {
        this.ids = ids;
    }

    public IdListDto setIds(List<String> ids) {
        this.ids = ids;
        return this;
    }

    public List<String> getIds() {
        return ids;
    }

    public IdListDto addId(String id) {
        ids.add(id);
        return this;
    }

}
