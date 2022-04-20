package com.group8rhea.monopolyserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordDto {
    private Integer resettoken;
    private String newPassword;
}
