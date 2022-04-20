package com.group8rhea.monopolyserver.service;

import com.group8rhea.monopolyserver.dto.MailDto;
import com.group8rhea.monopolyserver.dto.ResetPasswordDto;
import com.group8rhea.monopolyserver.dto.SignInDto;
import com.group8rhea.monopolyserver.dto.SignUpDto;
import com.group8rhea.monopolyserver.model.UserModelEntity;
import com.group8rhea.monopolyserver.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.Optional;
import java.util.Random;

@Service
public class RegisterLoginServices implements Serializable {

    @Autowired
    private UserModelRepository userModelRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final Random random = new Random();

    @Autowired
    MailServerService mailServerService ;

    /* TODO: Add verification of email password etc. regex*/
    public HttpStatus registerUser(SignUpDto userDto){
        UserModelEntity userModel = new UserModelEntity(userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getEmail());
        if (userModelRepository.findByUsername(userModel.getUsername()).isEmpty()) {
            userModelRepository.save(userModel);
            System.out.println("Succesfull add to repository");
            return HttpStatus.OK;
        }
        else{
            System.out.println("This user already exists");
            return HttpStatus.OK;
        }
    }

    public HttpStatus loginUser(SignInDto userDto){
        Optional<UserModelEntity> userModel1 = userModelRepository.findByUsername(userDto.getUsername());
        if ( userModel1.isPresent() ){
            return HttpStatus.OK;
        }
        else{
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }
    public HttpStatus forgotPassword(String email){
        Optional<UserModelEntity> userModel = userModelRepository.findByEmail(email);
        if ( userModel.isPresent()){

            int resetPassword =generateRandomNum(100000,999999);
            System.out.println("rest pass: "+ resetPassword);

            userModel.get().setResettoken(resetPassword);
            userModelRepository.save(userModel.get());

            mailServerService.sendResetLinkMail(email,resetPassword);
            return HttpStatus.OK;
        }
        else{
            return HttpStatus.BAD_REQUEST;
        }
    }

    public HttpStatus resetPassword(ResetPasswordDto resetPasswordDto){
        Optional<UserModelEntity> userModel = userModelRepository.findByResettoken(resetPasswordDto.getResettoken());
        if ( userModel.isPresent() ){
            UserModelEntity userModelEntity = userModel.get();
            userModelEntity.setPassword(passwordEncoder.encode(resetPasswordDto.getNewPassword()));
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
//        return HttpStatus.OK;
    }

    private int generateRandomNum(int rangeFrom, int rangeTo ){
        return random.nextInt(rangeTo - rangeFrom +1 ) + rangeFrom ;
    }
}
