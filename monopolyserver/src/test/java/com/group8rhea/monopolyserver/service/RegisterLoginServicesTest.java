package com.group8rhea.monopolyserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group8rhea.monopolyserver.dto.HttpResponseDto;
import com.group8rhea.monopolyserver.dto.SignInDto;
import com.group8rhea.monopolyserver.dto.SignUpDto;
import com.group8rhea.monopolyserver.model.UserModelEntity;
import com.group8rhea.monopolyserver.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterLoginServicesTest {
    @Autowired
    RegisterLoginServices registerLoginServices;

    @MockBean
    UserModelRepository userModelRepository;

    @Test
    public void registerUserTest(){
        SignUpDto signUpDto = new SignUpDto("nedstark21", "nedstarkpass","nedstark@gmail.com");

        HttpResponseDto httpResponseDto = registerLoginServices.registerUser(signUpDto);
        Assert.assertEquals(HttpStatus.OK,httpResponseDto.getHttpStatus());
    }

    @Test
    public void loginUserTest(){
        SignUpDto signUpDto = new SignUpDto("nedstark21", "nedstarkpass","nedstark@gmail.com");
        registerLoginServices.registerUser(signUpDto);

        SignInDto signInDto = new SignInDto("nedstark21","nedstarkpass");
        HttpResponseDto httpResponseDto = registerLoginServices.loginUser(signInDto);
        Assert.assertEquals(HttpStatus.OK,httpResponseDto.getHttpStatus());
    }

    @Test
    public void forgotPassTest(){
        SignUpDto signUpDto = new SignUpDto("nedstark21", "nedstarkpass","nedstark@gmail.com");
        registerLoginServices.registerUser(signUpDto);

        HttpResponseDto httpResponseDto =  registerLoginServices.forgotPassword(signUpDto.getEmail());
        Assert.assertEquals(HttpStatus.OK,httpResponseDto.getHttpStatus());
    }

}
