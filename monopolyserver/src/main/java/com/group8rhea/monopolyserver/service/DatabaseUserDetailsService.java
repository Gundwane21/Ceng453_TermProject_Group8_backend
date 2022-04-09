package com.group8rhea.monopolyserver.service;

import com.group8rhea.monopolyserver.model.UserModel;
import com.group8rhea.monopolyserver.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserModelRepository userModelRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userModel =  userModelRepository.findByUsername(username);
        if(userModel.isEmpty()){
            throw new UsernameNotFoundException("Username "+ username+ " not found");
        }

        else{
//            UserDetails user = User.withUsername( userModel.get().getUsername())
//                    .password(userModel.get().getPassword())
//                    .authorities("USER")
//                    .build();
            UserDetails user =  User.withDefaultPasswordEncoder()
                    .username(userModel.get().getUsername())
                    .password(userModel.get().getPassword())
                    .roles("USER")
                    .build();
            return user;
        }
    }
}
