package com.bibliotheque.microservicemyusers.dao;

import com.bibliotheque.microservicemyusers.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Long> {

    //trouver un utilisateur par son pseudo
    Utilisateur findByPseudo(String pseudo);
}
