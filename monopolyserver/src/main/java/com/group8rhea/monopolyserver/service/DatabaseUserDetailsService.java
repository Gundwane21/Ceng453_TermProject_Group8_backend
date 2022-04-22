package com.group8rhea.monopolyserver.service;

import com.group8rhea.monopolyserver.dto.SignInDto;
import com.group8rhea.monopolyserver.dto.SignUpDto;
import com.group8rhea.monopolyserver.model.UserModelEntity;
import com.group8rhea.monopolyserver.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
* Service that is needed for Spring security Http basic authentification
* overrides the default loadByUsername method of Spring Securitys
* UserDetailsService interface
* During runtime daoAuthentification provider will use this service to
* check whether the user is authorized
* */
@Service
public class DatabaseUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserModelRepository userModelRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
    * @param  username
    * this method is used by DaoAuthentification provider to check the user is allowed
    * It checks whether the user in our User table in our dataset
    * throws exception if not found
    * @return UserDetails
    * returns UserDetails Instance to DaoAuthentification provider which is necesssary for Spring Security
    * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModelEntity> userModel =  userModelRepository.findByUsername(username);
        if(userModel.isEmpty()){
            throw new UsernameNotFoundException("Username "+ username+ " not found");
        }

        else{
            UserDetails user = User.withUsername( userModel.get().getUsername())
                    .password(userModel.get().getPassword())
                    .authorities("USER")
                    .build();
            return user;
        }
    }

}
