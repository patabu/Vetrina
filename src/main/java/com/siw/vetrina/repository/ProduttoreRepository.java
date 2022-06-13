package com.siw.vetrina.repository;

import org.springframework.data.repository.CrudRepository;

import com.siw.vetrina.model.Produttore;

public interface ProduttoreRepository extends CrudRepository<Produttore, Long> {
	public boolean existsByNomeAndSede(String nome, String sede);
}
