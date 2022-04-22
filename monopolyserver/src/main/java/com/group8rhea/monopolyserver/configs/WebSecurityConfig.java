package com.group8rhea.monopolyserver.configs;

import com.group8rhea.monopolyserver.service.DatabaseUserDetailsService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.authentication.AuthenticationProvider;
        import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
        import org.springframework.security.config.http.SessionCreationPolicy;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DatabaseUserDetailsService databaseUserDetailsService;

    /*
    * Defines /register /login /forgotPassword  /resetPassword  post request endpoints to be
    * not require authentification. All other requests require authentification
    * */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/login",
                        "/api/register",
                        "/api/forgotPassword",
                        "/api/resetPassword"
                        ).permitAll()
                .antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui/**",
                "/swagger-ui",
                "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

    }
    /*
    * Sets up the daoAuthentification provider to work with our UserDetails implementation
    * using same encyripter as the register for passwords
    * This is a key part of Http basic auth
    * */
    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(this.databaseUserDetailsService);
        return provider;
    }

    /*
    * Sets up the encoder to be used in encyripting the password*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}