package com.bibliotheque.microservicemyusers.controller;

import com.bibliotheque.microservicemyusers.dao.UtilisateurDao;
import com.bibliotheque.microservicemyusers.model.Utilisateur;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilisateurController {

    @Autowired
    UtilisateurDao utilisateurDao;

    //affiche la page d'acceuil
    @GetMapping("/acceuil")
    public String index() {

        return "Acceuil";
    }

    //affiche les donnees utilisateur
    @GetMapping("/profil")
    public String afficherLeProfilUtilisateur(Model model){

        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        return "Profil";
    }

}
