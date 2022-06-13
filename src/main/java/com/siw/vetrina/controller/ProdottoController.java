package com.siw.vetrina.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.siw.vetrina.model.Prodotto;
import com.siw.vetrina.service.ProdottoService;
import com.siw.vetrina.validation.ProdottoValidator;

public class ProdottoController {
	@Autowired
	private ProdottoService prodottoService;
	@Autowired
	private ProdottoValidator prodottoValidator;

	@PostMapping("/saveProdotto")
	public String saveProdotto(@Valid @ModelAttribute("prodotto") Prodotto prodotto, BindingResult br, Model model) {
		this.prodottoValidator.validate(prodotto, br);
		if (!br.hasErrors()) {
			prodottoService.saveProdotto(prodotto);
			model.addAttribute("prodotto", prodotto);
			return "prodotto.html";
		}
		return "prodottoForm.html";
	}

	@GetMapping("/prodotto/{id}")
	public String getProdotto(@PathVariable("id") Long id, Model model) {
		Prodotto c = prodottoService.getProdotto(id);
		model.addAttribute("prodotto", c);
		return "prodotto.html";
	}
}
