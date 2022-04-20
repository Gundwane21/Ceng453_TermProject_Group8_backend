package com.group8rhea.monopolyserver.controller;

import com.group8rhea.monopolyserver.dto.ForgotPasswordDto;
import com.group8rhea.monopolyserver.dto.ResetPasswordDto;
import com.group8rhea.monopolyserver.dto.SignInDto;
import com.group8rhea.monopolyserver.dto.SignUpDto;
import com.group8rhea.monopolyserver.service.DatabaseUserDetailsService;
import com.group8rhea.monopolyserver.service.RegisterLoginServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping(value = "/api")
class RegistrationController {

    private DatabaseUserDetailsService databaseUserDetailsService;
    private RegisterLoginServices registerLoginServices;

    RegistrationController(DatabaseUserDetailsService databaseUserDetailsService, RegisterLoginServices registerLoginServices){
        this.databaseUserDetailsService = databaseUserDetailsService;
        this.registerLoginServices= registerLoginServices;
    }

    @PostMapping(value = "register")
    public HttpStatus register(@RequestBody SignUpDto userDto){
        HttpStatus httpStatus = registerLoginServices.registerUser(userDto);
        return httpStatus;
    }

    @PostMapping(value = "login")
    public HttpStatus login(Authentication authRequest){
        if (authRequest !=null){

            User authRequestUser = (User) authRequest.getPrincipal();
            SignInDto signInDto = new SignInDto(authRequestUser.getUsername(),authRequestUser.getPassword());

            HttpStatus httpStatus =  registerLoginServices.loginUser( signInDto);
            return httpStatus;
        }
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        return httpStatus;
    }

    @PostMapping(value = "forgotPassword")
    public HttpStatus forgotPassword(@RequestBody ForgotPasswordDto forgotPasswordDto){
        System.out.println(forgotPasswordDto);
        return registerLoginServices.forgotPassword(forgotPasswordDto.getEmail());
    }

    @PostMapping(value = "resetPassword")
    public HttpStatus resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        return registerLoginServices.resetPassword(resetPasswordDto);
    }
}