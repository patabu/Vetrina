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

import com.siw.vetrina.model.Prodotto;
import com.siw.vetrina.model.Produttore;
import com.siw.vetrina.service.ProdottoService;
import com.siw.vetrina.service.ProduttoreService;
import com.siw.vetrina.validation.ProduttoreValidator;

@Controller
public class ProduttoreController {  
	@Autowired
	private ProdottoService prodottoService;
	@Autowired
	private ProduttoreService produttoreService;
	@Autowired
	private ProduttoreValidator produttoreValidator;
	@Autowired
	private ProdottoController prodottoController;

	@PostMapping("/admin/produttore/save") 
	public String saveProduttore(@Valid @ModelAttribute("produttore") Produttore produttore, BindingResult br, Model model) {
		this.produttoreValidator.validate(produttore, br);
		if (!br.hasErrors()) {
			produttoreService.saveProduttore(produttore);
			this.prodottoController.getProdotti(model);
		}
		return "produttoreForm.html";
	}
	@GetMapping("/admin/produttore/formAdd")
	public String formAddProduttore(Model model) {
		model.addAttribute("produttore", new Produttore());
		return "produttoreForm.html";
	}
	
	@GetMapping("/admin/produttore/formModify/{id}")
	public String formModifyProduttore(@PathVariable("id") Long id, Model model) {
		model.addAttribute("produttore", this.produttoreService.getProduttore(id));
		return "produttoreForm.html";
	}

	@GetMapping("/produttore/{id}")
	public String getProduttore(@PathVariable("id") Long id, Model model) {
		Produttore c = produttoreService.getProduttore(id);
		model.addAttribute("produttore", c);
		return "produttore.html";
	}
}
