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

	@PostMapping("/admin/categoria/save")
	public String saveCategoria(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult br, Model model) {
		this.categoriaValidator.validate(categoria, br);
		if (!br.hasErrors()) {
			categoriaService.saveCategoria(categoria);
			model.addAttribute("categoria", categoria);
			return "categoria.html";
		}
		return "categoriaForm.html";
	}
	@GetMapping("/admin/categoria/form")
	public String formCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "categoriaForm.html";
	}

	@GetMapping("/categoria/{id}")
	public String getCategoria(@PathVariable("id") Long id, Model model) {
		Categoria c = categoriaService.getCategoria(id);
		model.addAttribute("categoria", c);
		return "categoria.html";
	}
}
