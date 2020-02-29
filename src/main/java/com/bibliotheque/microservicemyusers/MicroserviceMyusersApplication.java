package com.bibliotheque.microservicemyusers;

import com.bibliotheque.microservicemyusers.dao.RoleDao;
import com.bibliotheque.microservicemyusers.dao.UtilisateurDao;
import com.bibliotheque.microservicemyusers.model.RoleEnum;
import com.bibliotheque.microservicemyusers.model.Utilisateur;
import com.bibliotheque.microservicemyusers.security.BCryptManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class MicroserviceMyusersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceMyusersApplication.class, args);
	}

	@Autowired
	UtilisateurDao utilisateurDao;


	@PostConstruct
	private void postConstruct(){
		utilisateurDao.save(new Utilisateur("martin", "martin", Arrays.asList(RoleEnum.USER)));
	}

}
