package com.matthewjohnson42.personalMemexService.data.dto;

import javax.validation.constraints.NotNull;

public class RawTextDTO {

    @NotNull
    String text;

    public RawTextDTO(String text) {
        this.text = text;
    }

    public RawTextDTO setText(String text) {
        this.text = text;
        return this;
    }

    public String getText() {
        return text;
    }

}
