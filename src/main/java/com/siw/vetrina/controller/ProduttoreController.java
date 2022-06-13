package com.siw.vetrina.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.siw.vetrina.model.Produttore;
import com.siw.vetrina.service.ProduttoreService;
import com.siw.vetrina.validation.ProduttoreValidator;

@Controller
public class ProduttoreController {
	@Autowired
	private ProduttoreService produttoreService;
	@Autowired
	private ProduttoreValidator produttoreValidator;

	@PostMapping("/saveProduttore")
	public String saveProduttore(@Valid @ModelAttribute("produttore") Produttore produttore, BindingResult br, Model model) {
		this.produttoreValidator.validate(produttore, br);
		if (!br.hasErrors()) {
			produttoreService.saveProduttore(produttore);
			model.addAttribute("produttore", produttore);
			return "produttore.html";
		}
		return "produttoreForm.html";
	}
	@GetMapping("/produttoreForm")
	public String formProduttore(Model model) {
		model.addAttribute("produttore", new Produttore());
		return "produttoreForm.html";
	}

	@GetMapping("/produttore/{id}")
	public String getProduttore(@PathVariable("id") Long id, Model model) {
		Produttore c = produttoreService.getProduttore(id);
		model.addAttribute("produttore", c);
		return "produttore.html";
	}
}
