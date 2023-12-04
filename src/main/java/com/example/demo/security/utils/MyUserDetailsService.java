package com.example.demo.security.utils;

import com.example.demo.security.entities.Utilisateur;
import com.example.demo.security.services.AccountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private AccountService accountService;

    public MyUserDetailsService(AccountService accountService){
        this.accountService=accountService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur=accountService.loadUserByName(username);
        Collection<GrantedAuthority> auths =new ArrayList<>();
        utilisateur.getRoles().stream().forEach(rol->auths.add(new SimpleGrantedAuthority(rol.getRole())));
        System.out.println(utilisateur);
        System.out.println(utilisateur.getPassword());

        User u= new User(utilisateur.getUsername(), utilisateur.getPassword(), auths);
        System.out.println(u);
return u;

    }
}
