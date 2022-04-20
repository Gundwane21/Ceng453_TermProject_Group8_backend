package com.group8rhea.monopolyserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpDto {
    private String username;
    private String password;
    private String email;
    public SignUpDto(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
