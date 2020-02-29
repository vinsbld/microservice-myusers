package com.bibliotheque.microservicemyusers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilisateurController {

    @GetMapping("/acceuil")
    public String index() {

        return "Acceuil";
    }

}
