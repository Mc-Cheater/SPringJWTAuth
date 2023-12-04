package com.example.demo.security.config;

import com.example.demo.security.entities.Utilisateur;
import com.example.demo.security.filters.JWTAuthenticationFilter;
import com.example.demo.security.services.AccountService;
import com.example.demo.security.utils.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collection;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,@Autowired AuthenticationManagerBuilder builder) throws Exception{

       // http.formLogin();
        http.authorizeHttpRequests(req->req.requestMatchers("/login**").permitAll());
        //http.addFilter(new JWTAuthenticationFilter(builder.getOrBuild()));

        http.addFilterBefore(new JWTAuthenticationFilter(builder.getOrBuild()), UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests(
            (authz)->{
                authz.anyRequest().authenticated();


            }
    ).csrf(AbstractHttpConfigurer::disable);
http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    return http.build();

    }

    @Autowired
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder, @Autowired MyUserDetailsService myUserDetailsService, @Autowired PasswordEncoder bCryptPasswordEncoder) throws Exception{
        authenticationManagerBuilder.userDetailsService(myUserDetailsService).passwordEncoder(bCryptPasswordEncoder);

//   return authenticationManagerBuilder.build();

    }
   /* @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }*/
}
