package com.group8rhea.monopolyserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SignUpDto {
    private String username;
    @Getter
    private String password;
    @Getter
    private String email;
    public SignUpDto(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
