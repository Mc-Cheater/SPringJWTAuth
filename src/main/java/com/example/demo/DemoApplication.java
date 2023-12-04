package com.example.demo;

import com.example.demo.security.entities.Roles;
import com.example.demo.security.entities.Utilisateur;
import com.example.demo.security.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner start(AccountService accountService){
		return args->{
			accountService.addNewRole(new Roles(null,"ADMIN"));
			accountService.addNewRole(new Roles(null,"USER"));
			accountService.addNewRole(new Roles(null,"CUSTOMER_MANAGER"));
			accountService.addNewRole(new Roles(null,"PRODUCT_MANAGER"));
			accountService.addNewRole(new Roles(null,"BILLS_MANAGER"));

			accountService.addNewUser(new Utilisateur(null,"anass","1234",new ArrayList<>()));
			accountService.addNewUser(new Utilisateur(null,"adil","1234",new ArrayList<>()));
			accountService.addNewUser(new Utilisateur(null,"simo","1234",new ArrayList<>()));
			accountService.addNewUser(new Utilisateur(null,"ahmed","1234",new ArrayList<>()));
			accountService.addNewUser(new Utilisateur(null,"hicham","1234",new ArrayList<>()));

			accountService.addRoleToUser("anass","USER");
			accountService.addRoleToUser("adil","USER");
			accountService.addRoleToUser("adil","ADMIN");
			accountService.addRoleToUser("simo","USER");
			accountService.addRoleToUser("simo","CUSTOMER_MANAGER");
			accountService.addRoleToUser("ahmed","USER");
			accountService.addRoleToUser("ahmed","PRODUCT_MANAGER");
			accountService.addRoleToUser("hicham","USER");
			accountService.addRoleToUser("hicham","BILLS_MANAGER");

		};
	}
}
