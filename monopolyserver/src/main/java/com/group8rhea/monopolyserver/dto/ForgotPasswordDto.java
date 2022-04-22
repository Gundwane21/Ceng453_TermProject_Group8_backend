package com.group8rhea.monopolyserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* Dto that is used in forget password action
* */
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordDto {
    String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
