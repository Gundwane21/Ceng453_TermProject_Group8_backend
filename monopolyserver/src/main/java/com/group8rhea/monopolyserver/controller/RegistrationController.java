package com.group8rhea.monopolyserver.controller;

import com.group8rhea.monopolyserver.dto.SignInDto;
import com.group8rhea.monopolyserver.dto.SignUpDto;
import com.group8rhea.monopolyserver.service.DatabaseUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
//@RequestMapping(value = "/api")
class RegistrationController {

    private DatabaseUserDetailsService databaseUserDetailsService;

    RegistrationController(DatabaseUserDetailsService databaseUserDetailsService){
        this.databaseUserDetailsService = databaseUserDetailsService;
    }

    @PostMapping(value = "register")
    public HttpStatus register(@RequestBody SignUpDto userDto){
        HttpStatus httpStatus = databaseUserDetailsService.registerUser(userDto);
        return httpStatus;
    }

    @PostMapping(value = "login")
    public HttpStatus login(Authentication authRequest){
        if (authRequest !=null){

            User authRequestUser = (User) authRequest.getPrincipal();
            SignInDto signInDto = new SignInDto(authRequestUser.getUsername(),authRequestUser.getPassword());

            HttpStatus httpStatus =  databaseUserDetailsService.loginUser( signInDto);
            return httpStatus;
        }
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        return httpStatus;
    }





}