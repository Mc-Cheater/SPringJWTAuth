package com.example.demo.security.services;

import com.example.demo.security.entities.Roles;
import com.example.demo.security.entities.Utilisateur;
import com.example.demo.security.repositories.RolesRepository;
import com.example.demo.security.repositories.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    private UtilisateurRepository userRepository;
    private RolesRepository rolesRepository;

    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(UtilisateurRepository userRepository,RolesRepository rolesRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.rolesRepository=rolesRepository;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public Utilisateur addNewUser(Utilisateur user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public Roles addNewRole(Roles role) {
        return rolesRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        Utilisateur temp=userRepository.findUtilisateurByUsername(username);
        Roles tempRole=rolesRepository.findByRole(role);
        temp.getRoles().add(tempRole);

    }

    @Override
    public Utilisateur loadUserByName(String username) {
        return userRepository.findUtilisateurByUsername(username);
    }

    @Override
    public List<Utilisateur> users() {
        return userRepository.findAll();
    }
}
