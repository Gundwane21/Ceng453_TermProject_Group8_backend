package com.group8rhea.monopolyserver.service;

import com.group8rhea.monopolyserver.dto.ForgotPasswordDto;
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

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterLoginServicesTest {

    @MockBean
    UserModelRepository userModelRepository;

    @Autowired
    RegisterLoginServices registerLoginServices  ;

    /*
    * Test the register service if the user isnt registered before
    * */
    @Test
    public void registerUserSuccessFullCreateTest(){
        SignUpDto signUpDto = new SignUpDto("nedstark21", "nedstarkpass","nedstark@gmail.com");

        HttpResponseDto httpResponseDto = registerLoginServices.registerUser(signUpDto);
        when(userModelRepository.findByUsername(signUpDto.getUsername())).thenReturn(Optional.empty());
        Assert.assertEquals(signUpDto.getUsername(),httpResponseDto.getUsername());
        Assert.assertEquals("Succesfull add",httpResponseDto.getMessage());
    }

    /*
     * Test the register service if the user is already registered before
     * */
    @Test
    public void registerUserAlreadyExistTest(){
        SignUpDto signUpDto = new SignUpDto("AryaTheKing", "killJoffrey","aryathekingslayer@gmail.com");
        UserModelEntity userModelEntity = new UserModelEntity();
        userModelEntity.setUsername("AryaTheKing");
        userModelEntity.setPassword("killJoffrey");
        userModelEntity.setEmail("aryathekingslayer@gmail.com");

        when(userModelRepository.findByUsername(signUpDto.getUsername()))
                .thenReturn(Optional.of(userModelEntity));
        HttpResponseDto httpResponseDto = registerLoginServices.registerUser(signUpDto);
        Assert.assertEquals(signUpDto.getUsername(),httpResponseDto.getUsername());
        Assert.assertEquals("Already exists",httpResponseDto.getMessage());
    }

    /*
     * Test the login service if the user has correct credentials
     * */

    @Test
    public void loginUserSuccessTest(){
        SignInDto signInDto = new SignInDto("Sansaa","loveJeff");
        UserModelEntity userModelEntity = new UserModelEntity();
        userModelEntity.setUsername("Sansaa");
        userModelEntity.setPassword("loveJeff");
        when(userModelRepository.findByUsername(signInDto.getUsername()))
                .thenReturn(Optional.of(userModelEntity));
        HttpResponseDto httpResponseDto = registerLoginServices.loginUser(signInDto);
        Assert.assertEquals(HttpStatus.OK,httpResponseDto.getHttpStatus());
        Assert.assertEquals("Login Success",httpResponseDto.getMessage());
    }

    /*
     * Test the login service if the user does not have correct credentials
     * */

    @Test
    public void loginUserFailTest(){
        SignInDto signInDto = new SignInDto("Tyrion","MissStannis");

        when(userModelRepository.findByUsername(signInDto.getUsername()))
                .thenReturn(Optional.empty());
        HttpResponseDto httpResponseDto = registerLoginServices.loginUser(signInDto);
        Assert.assertEquals(HttpStatus.UNAUTHORIZED,httpResponseDto.getHttpStatus());
        Assert.assertEquals("Username and/or Password is incorrect",httpResponseDto.getMessage());
    }

    /*
     * Test the forgetPassword service if the users email is in the database
     * */
    @Test
    public void forgotPassSuccessTest(){
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto();
        forgotPasswordDto.setEmail("nolegs_sadtheon@gmail.com");

        UserModelEntity userModelEntity = new UserModelEntity();
        userModelEntity.setEmail("nolegs_sadtheon@gmail.com");
        when(userModelRepository.findByEmail(forgotPasswordDto.getEmail()))
            .thenReturn(Optional.of(userModelEntity));
        HttpResponseDto httpResponseDto =  registerLoginServices.forgotPassword(forgotPasswordDto.getEmail());
        Assert.assertEquals(HttpStatus.OK,httpResponseDto.getHttpStatus());
        Assert.assertEquals("Reset link is sent",httpResponseDto.getMessage());
    }

    /*
     * Test the forgetPassword service if the users email is not in the database
     * */

    @Test
    public void forgotPassFailTest(){
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto();
        forgotPasswordDto.setEmail("happyramseygmail.com");
        when(userModelRepository.findByEmail(forgotPasswordDto.getEmail()))
                .thenReturn(Optional.empty());
        HttpResponseDto httpResponseDto =  registerLoginServices.forgotPassword(forgotPasswordDto.getEmail());
        Assert.assertEquals(HttpStatus.NOT_FOUND,httpResponseDto.getHttpStatus());
        Assert.assertEquals("Email does not exists",httpResponseDto.getMessage());
    }


}
