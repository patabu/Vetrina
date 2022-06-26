package com.siw.vetrina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.siw.vetrina.model.Credentials;
import com.siw.vetrina.model.Utente;
import com.siw.vetrina.service.CredentialsService;

@Controller
public class AuthenticationController { 
	
	@Autowired private CredentialsService credentialsService;
	
	@GetMapping("/register")
	public String showRegisterForm (Model model) {
		model.addAttribute("utente", new Utente());
		model.addAttribute("credentials", new Credentials());
		return "registerForm";
	}
	
	@GetMapping(value = {"/login", "/", "/index", "/logout"})
	public String showLoginForm (Model model) {
		return "loginForm"; 
	} 
	
    @PostMapping("/register") 
    public String registerUser(@ModelAttribute("utente") Utente utente,
                 BindingResult userBindingResult,
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {

        // validate user and credentials fields
        //this.userValidator.validate(user, userBindingResult);
        //this.credentialsValidator.validate(credentials, credentialsBindingResult);

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
            credentials.setUtente(utente);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("username", credentials.getUsername()); 
            return "loginForm";
        }
        return "registerForm";
    }
}