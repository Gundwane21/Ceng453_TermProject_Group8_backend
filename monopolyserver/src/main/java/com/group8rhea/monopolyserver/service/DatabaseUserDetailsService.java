package com.group8rhea.monopolyserver.service;

import com.group8rhea.monopolyserver.dto.SignInDto;
import com.group8rhea.monopolyserver.dto.SignUpDto;
import com.group8rhea.monopolyserver.model.UserModel;
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
        Optional<UserModel> userModel =  userModelRepository.findByUsername(username);
        if(userModel.isEmpty()){
            throw new UsernameNotFoundException("Username "+ username+ " not found");
        }

        else{
            UserDetails user = User.withUsername( userModel.get().getUsername())
                    .password(userModel.get().getPassword())
                    .authorities("USER")
                    .build();
//            UserDetails user =  User.withDefaultPasswordEncoder()
//                    .username(userModel.get().getUsername())
//                    .password(userModel.get().getPassword())
//                    .roles("USER")
//                    .build();
            return user;
        }
    }

    /* TODO: Add verification of email password etc. regex*/
    public HttpStatus registerUser(SignUpDto userDto){
        UserModel userModel = new UserModel(userDto.getUsername(),
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
        Optional<UserModel> userModel1 = userModelRepository.findByUsername(userDto.getUsername());
        System.out.println(userModel1.get());
        if ( userModel1.isPresent() ){
            return HttpStatus.OK;
        }
        else{
            System.out.println("THIS USER" + "IS NOT AUTHORIZED");
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }
}
