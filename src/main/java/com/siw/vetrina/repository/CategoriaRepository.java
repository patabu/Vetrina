package com.siw.vetrina.repository;

import org.springframework.data.repository.CrudRepository;

import com.siw.vetrina.model.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
	public boolean existsByNome(String nome);
}
