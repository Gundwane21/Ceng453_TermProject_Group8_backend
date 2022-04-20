package com.group8rhea.monopolyserver.dto;

import lombok.Getter;

@Getter
public class SignInDto {
    private String username;
    private String password;  

    public SignInDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
