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

import com.siw.vetrina.model.Categoria;
import com.siw.vetrina.service.CategoriaService;
import com.siw.vetrina.validation.CategoriaValidator;
@Controller
public class CategoriaController { 
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private CategoriaValidator categoriaValidator;
	@Autowired
	private ProdottoController prodottoController;

	@PostMapping("/admin/categoria/save")  
	public String saveCategoria(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult br, Model model) {
		this.categoriaValidator.validate(categoria, br);
		if (!br.hasErrors()) {
			categoriaService.saveCategoria(categoria);
			return this.prodottoController.getProdotti(model);  
		}
		return "categoriaForm.html";
	} 
	  
	@GetMapping("/admin/categoria/formAdd")
	public String formAddCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "categoriaForm.html";
	}  
	@GetMapping("/admin/categoria/formModify/{id}")
	public String formModifyCategoria(@PathVariable("id") Long id, Model model) {
		model.addAttribute("categoria", this.categoriaService.getCategoria(id));
		model.addAttribute("modifyForm", true); 
		return "categoriaForm.html";
	}

	@GetMapping("/categoria/{id}")
	public String getCategoria(@PathVariable("id") Long id, Model model) {
		Categoria c = categoriaService.getCategoria(id);
		model.addAttribute("categoria", c);
		return "categoria.html";
	}
	
	@GetMapping("/admin/categoria/get/all")
	public String getCategorie(Model model) {
		model.addAttribute("categorie", this.categoriaService.getAllCategorie());
		return "categorie.html";
	}
	
	@GetMapping("/admin/categoria/delete/{id}")
	public String deleteCategoria(@PathVariable("id") Long id, Model model) {
		this.categoriaService.removeCategoria(id);
		return this.getCategorie(model);
	}
	
}
