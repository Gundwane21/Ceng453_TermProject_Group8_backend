package com.group8rhea.monopolyserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.group8rhea.monopolyserver.dto.*;
import com.group8rhea.monopolyserver.model.UserModelEntity;
import com.group8rhea.monopolyserver.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class RegisterLoginServices implements Serializable {

    @Autowired
    private UserModelRepository userModelRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final Random random = new Random();

    @Autowired
    MailServerService mailServerService ;


    public HttpResponseDto registerUser(SignUpDto userDto){
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setUsername(userDto.getUsername());

        UserModelEntity userModel = new UserModelEntity(userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getEmail());
        if (userModelRepository.findByUsername(userModel.getUsername()).isEmpty()) {
            userModelRepository.save(userModel);
            httpResponseDto.setHttpStatus(HttpStatus.OK);
            httpResponseDto.setMessage("Succesfull add");
        }
        else{
            httpResponseDto.setHttpStatus(HttpStatus.CONFLICT);
            httpResponseDto.setMessage("Already exists");
        }
        return httpResponseDto;
    }

    public HttpResponseDto loginUser(SignInDto userDto){
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setUsername(userDto.getUsername());

        Optional<UserModelEntity> userModel1 = userModelRepository.findByUsername(userDto.getUsername());
        if ( userModel1.isPresent() ){
            httpResponseDto.setHttpStatus(HttpStatus.OK);
            httpResponseDto.setMessage("Login Success");

        }
        else{
            httpResponseDto.setHttpStatus(HttpStatus.UNAUTHORIZED);
            httpResponseDto.setMessage("");
        }
        return httpResponseDto;
    }
    public HttpResponseDto forgotPassword(String email){
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setUsername(email);

        Optional<UserModelEntity> userModel = userModelRepository.findByEmail(email);
        if ( userModel.isPresent()){

            int resetPassword =generateRandomNum(100000,999999);
            System.out.println("rest pass: "+ resetPassword);

            userModel.get().setResettoken(resetPassword);
            userModelRepository.save(userModel.get());
            mailServerService.sendResetLinkMail(email,resetPassword);

            httpResponseDto.setHttpStatus(HttpStatus.OK);
            httpResponseDto.setMessage("Reset link is sent");
        }
        else{
            httpResponseDto.setHttpStatus(HttpStatus.NOT_FOUND);
            httpResponseDto.setMessage("email does not exists");
        }
        return httpResponseDto;
    }

    public HttpResponseDto resetPassword(ResetPasswordDto resetPasswordDto){
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setUsername("");

        Optional<UserModelEntity> userModel = userModelRepository.findByResettoken(resetPasswordDto.getResettoken());
        if ( userModel.isPresent() ){
            UserModelEntity userModelEntity = userModel.get();
            userModelEntity.setPassword(passwordEncoder.encode(resetPasswordDto.getNewPassword()));
            httpResponseDto.setHttpStatus(HttpStatus.OK);
            httpResponseDto.setMessage("Password reset");
        }
        else{
            httpResponseDto.setHttpStatus(HttpStatus.CONFLICT);
            httpResponseDto.setMessage("Failed reset");
        }
        return httpResponseDto;
    }

    private int generateRandomNum(int rangeFrom, int rangeTo ){
        return random.nextInt(rangeTo - rangeFrom +1 ) + rangeFrom ;
    }
}
