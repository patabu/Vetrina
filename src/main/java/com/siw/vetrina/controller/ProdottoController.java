package com.siw.vetrina.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.siw.vetrina.dto.SearchProdotti;
import com.siw.vetrina.model.Categoria;
import com.siw.vetrina.model.Prodotto;
import com.siw.vetrina.model.Produttore;
import com.siw.vetrina.service.CategoriaService;
import com.siw.vetrina.service.CredentialsService;
import com.siw.vetrina.service.ProdottoService;
import com.siw.vetrina.service.ProduttoreService;
import com.siw.vetrina.validation.ProdottoValidator;

@Controller
public class ProdottoController { 
	
	@Autowired private ProdottoService prodottoService;
	@Autowired private ProduttoreService produttoreService;
	@Autowired private ProdottoValidator prodottoValidator;
	@Autowired private CredentialsService credentialsService;
	@Autowired private CategoriaService categoriaService;

	@PostMapping("/admin/prodotto/save") 
	public String saveProdotto(@Valid @ModelAttribute("prodotto") Prodotto prodotto, BindingResult br, Model model) {
		this.prodottoValidator.validate(prodotto, br);
		if (!br.hasErrors()) {
			prodottoService.saveProdotto(prodotto);
			return this.getProdotti(model);
		}
		return "prodottoForm.html"; 
	}

	@GetMapping("/admin/prodotto/formAdd")  
	public String formAddProdotto(Model model) {
		model.addAttribute("prodotto", new Prodotto());
		model.addAttribute("categorie", this.categoriaService.getAllCategorie());
		model.addAttribute("produttori", this.produttoreService.getAllProduttori());
		return "prodottoForm.html";
	}
	
	@GetMapping("/admin/prodotto/formModify/{id}") 
	public String formModifyProdotto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("prodotto", this.prodottoService.getProdotto(id));
		model.addAttribute("categorie", this.categoriaService.getAllCategorie());
		model.addAttribute("produttori", this.produttoreService.getAllProduttori());
		return "prodottoForm.html"; 
	}
	
	@GetMapping("/prodotto/get/{id}")
	public String getProdotto(@PathVariable("id") Long id, Model model) {
		Prodotto prodotto = prodottoService.getProdotto(id);
		model.addAttribute("prodotto", prodotto);
		return "prodotto.html";
	}
	
	@GetMapping("/admin/prodotto/delete/{id}") 
	public String deleteProdotto(@PathVariable("id") Long id, Model model) {
		this.prodottoService.removeProdotto(id);
		return this.getProdotti(model);
	}
	
	
	@GetMapping("/prodotti") 
	public String getProdotti(Model model) { 
		this.prodottoService.getHomeData(model, this.prodottoService.getAllProdotti());
		return "prodotti.html";  
	} 

	
	@GetMapping("/prodotto/get/filtered") 
	public String getProdottiFiltered(@ModelAttribute("SearchProdotti") SearchProdotti searchProdotti, Model model) {
		this.prodottoService.getHomeData(model, this.prodottoService.searchProdotti(searchProdotti));
		this.credentialsService.setRoleInModel(model);
		return "prodotti.html";     
	}  
	

}
