package com.bibliotheque.microservicemyusers.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ConnexionController {

    final static Logger logger = LogManager.getLogger();

    @GetMapping(value = "/Connexion")
    public ModelAndView loginGet(Model model, @RequestParam(value = "error", required = false)String error, @RequestParam(value = "logout", required = false) String logout){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String errorMessage = null;
        if(error != null) {
            logger.error("l'utilisateur a saisi un pseudo ou un mot de passe incorrect");
            errorMessage = "Pseudo ou mot de passe incorrects !";
        }
        if(logout != null) {
            errorMessage = "Vous avez été déconecté avec succès !";
            logger.info("l'utilisateur c'est déconecté");
        }
        model.addAttribute("errorMessge", errorMessage);

        if(!(auth instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/index");
        }
        return new ModelAndView("Connexion");
    }

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }

}
