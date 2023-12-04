package com.example.demo.security.services;

import com.example.demo.security.entities.Roles;
import com.example.demo.security.entities.Utilisateur;

import java.util.List;

public interface AccountService {
    Utilisateur addNewUser(Utilisateur user);
    Roles addNewRole(Roles role);
    void addRoleToUser(String username,String role);
    Utilisateur loadUserByName(String username);
    List<Utilisateur> users();
}
