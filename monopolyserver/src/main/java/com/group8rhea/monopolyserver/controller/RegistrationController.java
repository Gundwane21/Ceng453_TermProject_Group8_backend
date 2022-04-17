package com.group8rhea.monopolyserver.controller;

import com.group8rhea.monopolyserver.MonopolyServerApplication;
import com.group8rhea.monopolyserver.dto.UserDto;
import com.group8rhea.monopolyserver.model.UserModel;
import com.group8rhea.monopolyserver.service.DatabaseUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.group8rhea.monopolyserver.repository.UserModelRepository;


@RestController
@RequestMapping(value = "/api")
class RegistrationController {

    private DatabaseUserDetailsService databaseUserDetailsService;

    RegistrationController(DatabaseUserDetailsService databaseUserDetailsService){
        this.databaseUserDetailsService = databaseUserDetailsService;
    }

    @PostMapping(value = "register",consumes = "application/json", produces =
            "application/json")
//    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public String register(@RequestBody UserDto userDto){
        databaseUserDetailsService.registerUser(userDto);
        System.out.println("SUCCESSFULLL REGISTER, " + " username: "
        + userDto.getUsername() + " password: " + userDto.getPassword()     );
        return "Succesful post request";
    }



}