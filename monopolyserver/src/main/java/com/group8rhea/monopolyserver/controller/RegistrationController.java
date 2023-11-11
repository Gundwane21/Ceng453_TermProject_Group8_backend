package com.group8rhea.monopolyserver.controller;

import com.group8rhea.monopolyserver.dto.*;
import com.group8rhea.monopolyserver.service.DatabaseUserDetailsService;
import com.group8rhea.monopolyserver.service.RegisterLoginServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for RESTful apis for register login password reset
 */

@RestController
@RequestMapping(value = "/api")
@Tag(name="Register and Login API", description = "Controlls the register, login and password reset activities")
public class RegistrationController {

    private DatabaseUserDetailsService databaseUserDetailsService;
    private RegisterLoginServices registerLoginServices;

    RegistrationController(DatabaseUserDetailsService databaseUserDetailsService, RegisterLoginServices registerLoginServices){
        this.databaseUserDetailsService = databaseUserDetailsService;
        this.registerLoginServices= registerLoginServices;
    }

    @Operation(description = "Registers the user with by taking http body as json which has username, password and email keys")
    @PostMapping(value = "register")
    @ApiResponse(responseCode = "201", description = "Returns the succesfull add response", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "409", description = "Returns the user already exists response", content = @Content(mediaType = "application/json"))
    public HttpResponseDto register(@RequestBody SignUpDto userDto){
        System.out.println('test-pr')
    
        return registerLoginServices.registerUser(userDto);
    }
    @Operation(description = "Logins the user with Http Basic Authentification", security = {@SecurityRequirement(name="Basic-Authentification")})
    @PostMapping(value = "login")
    @ApiResponse(responseCode = "200", description = "Returns the succesfull login response ", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "401", description = "Returns the Username and/or Password is incorrect response ", content = @Content(mediaType = "application/json"))
    public HttpResponseDto login(Authentication authRequest){
        if (authRequest !=null){

            User authRequestUser = (User) authRequest.getPrincipal();
            SignInDto signInDto = new SignInDto(authRequestUser.getUsername(),authRequestUser.getPassword());

            return   registerLoginServices.loginUser( signInDto);
        }
        return  new HttpResponseDto(HttpStatus.UNAUTHORIZED,"","");
    }

    @Operation(description = "If the email exists sends a reset email")
    @PostMapping(value = "forgotPassword")
    @ApiResponse(responseCode = "200", description = "Returns the Reset link sent response ", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Returns the email not found response ", content = @Content(mediaType = "application/json"))
    public HttpResponseDto forgotPassword(@RequestBody ForgotPasswordDto forgotPasswordDto){
        return registerLoginServices.forgotPassword(forgotPasswordDto.getEmail());
    }

    @Operation(description = "If the token provided by the user correct updates the password of the user")
    @PostMapping(value = "resetPassword")
    @ApiResponse(responseCode = "200", description = "Returns the password updated response ", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Returns the resettoken is wrong response ", content = @Content(mediaType = "application/json"))
    public HttpResponseDto resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        return registerLoginServices.resetPassword(resetPasswordDto);
    }
}