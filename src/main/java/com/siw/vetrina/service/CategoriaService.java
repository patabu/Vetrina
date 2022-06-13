package com.siw.vetrina.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siw.vetrina.model.Categoria;
import com.siw.vetrina.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Transactional
	public void saveCategoria(Categoria categoria) {
		this.categoriaRepository.save(categoria);

	}

	public Categoria getCategoria(Long id) {
		return categoriaRepository.findById(id).get();
	}

	public List<Categoria> getAllCategorie() {
		List<Categoria> categorie = new ArrayList<Categoria>();
		for (Categoria c : categoriaRepository.findAll())
			categorie.add(c);
		return categorie;
	}

	public void removeCategoria(Long id) {
		categoriaRepository.deleteById(id);
	}

	public boolean alreadyExist(Categoria target) {
		return this.categoriaRepository.existsByNome(target.getNome());
	}
}