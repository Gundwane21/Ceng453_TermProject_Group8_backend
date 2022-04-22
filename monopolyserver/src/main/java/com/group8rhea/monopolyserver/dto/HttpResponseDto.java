package com.group8rhea.monopolyserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class HttpResponseDto {

    @Getter
    @Setter
    private HttpStatus httpStatus;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String message;
    public  HttpResponseDto(){}
    public HttpResponseDto(HttpStatus httpStatus, String username, String message){
        this.httpStatus = httpStatus;
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
