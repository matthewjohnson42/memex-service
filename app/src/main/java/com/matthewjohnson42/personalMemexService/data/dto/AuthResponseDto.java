package com.matthewjohnson42.personalMemexService.data.dto;

public class AuthResponseDto {

    private String token;

    public AuthResponseDto() { }

    public String getToken() {
        return token;
    }

    public AuthResponseDto setToken(String token) {
        this.token = token;
        return this;
    }

}
