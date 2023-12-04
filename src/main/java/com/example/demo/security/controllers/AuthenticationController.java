package com.example.demo.security.controllers;

import com.example.demo.security.entities.Roles;
import com.example.demo.security.entities.Utilisateur;
import com.example.demo.security.services.AccountService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class AuthenticationController {
    AccountService accountService;

    public AuthenticationController(AccountService accountService){
        this.accountService=accountService;
    }

    @GetMapping("/utilisateurs")
    public List<Utilisateur> users(){
    return accountService.users();
    }

    @PostMapping("/utilisateurs")
    public Utilisateur addUser(@RequestBody  Utilisateur user){
        return accountService.addNewUser(user);
    }

    @PostMapping("/roles")
    public Roles addRole( @RequestBody Roles role){
        return accountService.addNewRole(role);
    }

    @PostMapping("/addRoleToUser")
    public void addRoleToUser(@RequestBody UserRole userRole){
        accountService.addRoleToUser(userRole.getUsername(),userRole.getRole());
    }

}

@Data
class UserRole{
    private String username;
    private String role;
}