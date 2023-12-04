package com.example.demo.security.repositories;

import com.example.demo.security.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
   Utilisateur findUtilisateurByUsername(String username);
   //List<Utilisateur> findAll();
  // Utilisateur save(com.example.demo.security.entities.Utilisateur user);
}
