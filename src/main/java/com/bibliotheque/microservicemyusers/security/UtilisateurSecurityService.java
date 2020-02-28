package com.bibliotheque.microservicemyusers.security;

import com.bibliotheque.microservicemyusers.dao.UtilisateurDao;
import com.bibliotheque.microservicemyusers.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurSecurityService implements UserDetailsService {

    private final UtilisateurDao utilisateurDao;

    @Autowired
    public UtilisateurSecurityService(UtilisateurDao utilisateurDao) {

        this.utilisateurDao = utilisateurDao;
    }


    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurDao.findByPseudo(pseudo.toLowerCase());
        if (utilisateur==null){
            throw new UsernameNotFoundException("l'utilisateur "+pseudo+" n'existe pas");
        }else {
            return utilisateur;
        }
    }
}
