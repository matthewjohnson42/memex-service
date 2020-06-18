package com.matthewjohnson42.personalMemexService.data.dto;

import javax.validation.constraints.NotBlank;

public class RawTextSummaryDTO {

    @NotBlank
    String id;

    public RawTextSummaryDTO(String id) {
        this.id = id;
    }

    public RawTextSummaryDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

}
