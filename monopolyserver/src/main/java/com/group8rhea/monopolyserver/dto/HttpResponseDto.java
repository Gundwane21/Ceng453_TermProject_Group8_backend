package com.group8rhea.monopolyserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponseDto {
    private HttpStatus httpStatus;
    private String username;
    private String message;
}