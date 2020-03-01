package com.bibliotheque.microservicemyusers.controller;

import com.bibliotheque.microservicemyusers.dao.UtilisateurDao;
import com.bibliotheque.microservicemyusers.model.Utilisateur;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Api(description = "Gestion des informations Utilisateurs")
@Controller
public class UtilisateurController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UtilisateurDao utilisateurDao;

    @ApiOperation("Affiche la page d'acceuil")
    @GetMapping("/acceuil")
    public String index() {

        return "Acceuil";
    }

    @ApiOperation("Récupère et affiche les donnees d'un utilisateur connecté")
    @GetMapping("/profil")
    public String afficherUnProfilUtilisateur(Model model){

        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);
        logger.info("l'utilisateur "+utilisateur.getPseudo()+" id:"+utilisateur.getId()+" consulte les informations de son compte utilisateur.");

        return "Profil";
    }

}
