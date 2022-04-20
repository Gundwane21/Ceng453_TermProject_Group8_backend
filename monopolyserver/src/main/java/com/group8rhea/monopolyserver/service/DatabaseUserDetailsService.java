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

@Service
public class DatabaseUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserModelRepository userModelRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
