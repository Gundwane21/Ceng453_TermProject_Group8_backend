package com.group8rhea.monopolyserver.dto;

import lombok.Getter;
import lombok.Setter;

public class ResetPasswordDto {
    private Integer resettoken;
    private String newPassword;

    public Integer getResettoken() {
        return resettoken;
    }

    public void setResettoken(Integer resettoken) {
        this.resettoken = resettoken;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
