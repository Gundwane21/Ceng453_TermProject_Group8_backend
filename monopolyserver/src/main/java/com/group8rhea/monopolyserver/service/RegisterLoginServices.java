package com.group8rhea.monopolyserver.service;

import com.group8rhea.monopolyserver.dto.*;
import com.group8rhea.monopolyserver.model.UserModelEntity;
import com.group8rhea.monopolyserver.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.net.http.HttpResponse;
import java.util.*;

/**Service that is used by RegistrationController to reach the database
* on a service level. Provides register login forgetPassword and update password services
* to the corresponding controllers
* */
@Service
public class RegisterLoginServices implements Serializable {

    @Autowired
    private UserModelRepository userModelRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final Random random = new Random();

    RegisterLoginServices(UserModelRepository userModelRepository){
        this.userModelRepository = userModelRepository;
    }

    @Autowired
    MailServerService mailServerService ;

    /**
     * @param signUpDto
     * SignUpDto contains :
     * {
     *  String username;
     *  String password;
     *  String email;
     *  }
     * example: {"username12", "pass12", "user@gmail.com"}
     * @return HttpResponseDto - Has 3 fields: HTTP Status code, username, response message
     * if the given username exists in the database it returns HTTP.Conflict and user "Already exists response
     * else it saves the user in the database and returns HTTP 201 Created and Succesfull add response
     * */
    public HttpResponseDto registerUser(SignUpDto signUpDto){
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setUsername(signUpDto.getUsername());

        UserModelEntity userModel = new UserModelEntity(signUpDto.getUsername(),
                passwordEncoder.encode(signUpDto.getPassword()),
                signUpDto.getEmail());
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
    /**
     * @param signInDto
     * SignInDto contains :
     * {
     *  String username;
     *  String password;
     *  }
     * example: {"username12", "pass12"}
     * @return HttpResponseDto - Has 3 fields: HTTP Status code, username, response message
     * Spring Security daoAuthentification Provider checks the credentials of user but additional check is made by
     * this service.
     * if the given username exists in the database it returns HTTP 200 Login Success response
     * else returns HTTP 401  response Username and/or Password is incorrect
     * */
    public HttpResponseDto loginUser(SignInDto signInDto){
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setUsername(signInDto.getUsername());

        Optional<UserModelEntity> userModel1 = userModelRepository.findByUsername(signInDto.getUsername());
        if ( userModel1.isPresent() ){
            httpResponseDto.setHttpStatus(HttpStatus.OK);
            httpResponseDto.setMessage("Login Success");

        }
        else{
            httpResponseDto.setHttpStatus(HttpStatus.UNAUTHORIZED);
            httpResponseDto.setMessage("Username and/or Password is incorrect");
        }
        return httpResponseDto;
    }

    /**
     * @param email - users email to send forgot password link
     * @return HttpResponseDto - Has 3 fields: HTTP Status code, username, response message
     *
     * if the given email exists in the database it generates a 6 digit random token
     * and sends an e mail to containing this resettoken
     * returns HTTP 200 Reset link is sent response
     * else returns HTTP 404 Email does not exists response
     * */
    public HttpResponseDto forgotPassword(String email){
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setUsername(email);

        Optional<UserModelEntity> userModel = userModelRepository.findByEmail(email);
        if ( userModel.isPresent()){

            int resetPassword =generateRandomNum(100000,999999);

            userModel.get().setResettoken(resetPassword);
            userModelRepository.save(userModel.get());
            mailServerService.sendResetLinkMail(email,resetPassword);

            httpResponseDto.setHttpStatus(HttpStatus.OK);
            httpResponseDto.setMessage("Reset link is sent");
        }
        else{
            httpResponseDto.setHttpStatus(HttpStatus.NOT_FOUND);
            httpResponseDto.setMessage("Email does not exists");
        }
        return httpResponseDto;
    }
    /**
    * @param resetPasswordDto
    * {
    *  Integer resettoken;
    *  String newPassword;
    *  }
    * example: {"128349", "passneww"}
     * @return HttpResponseDto - Has 3 fields: HTTP Status code, username, response message resetPasswordDto contains :
     * If the the token is same as the database updates the new password with same
    * encypter and returns Http 200 Password reset response
    * Else returns Http 404 Failed reset password, token is incorrect response
    * */
    public HttpResponseDto resetPassword(ResetPasswordDto resetPasswordDto){
        HttpResponseDto httpResponseDto = new HttpResponseDto();
        httpResponseDto.setUsername("");

        System.out.println(resetPasswordDto.getNewPassword());
        System.out.println(resetPasswordDto.getResettoken());
        Optional<UserModelEntity> userModel = userModelRepository.findByResettoken(resetPasswordDto.getResettoken());
        if ( userModel.isPresent() ){
            UserModelEntity userModelEntity = userModel.get();
            userModelEntity.setPassword(passwordEncoder.encode(resetPasswordDto.getNewPassword()));
            userModelRepository.save(userModelEntity);
            httpResponseDto.setHttpStatus(HttpStatus.OK);
            httpResponseDto.setMessage("Password reset");
        }
        else{
            httpResponseDto.setHttpStatus(HttpStatus.NOT_FOUND);
            httpResponseDto.setMessage("Failed reset password, token is incorrect");
        }
        return httpResponseDto;
    }

    /**
    * @param rangeFrom
     *  @param rangeTo
    * @return randam integer
    * generates a random integer given range
    * */
    private int generateRandomNum(int rangeFrom, int rangeTo ){
        return random.nextInt(rangeTo - rangeFrom +1 ) + rangeFrom ;
    }
}
