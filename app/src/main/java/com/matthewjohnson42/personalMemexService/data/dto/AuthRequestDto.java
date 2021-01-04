package com.matthewjohnson42.personalMemexService.data.dto;

public class AuthRequestDto {

    private String username;
    private String password;

    public AuthRequestDto() { }

    public String getUsername() {
        return username;
    }

    public AuthRequestDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AuthRequestDto setPassword(String password) {
        this.password = password;
        return this;
    }

}
