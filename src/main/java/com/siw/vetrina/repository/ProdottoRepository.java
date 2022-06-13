package com.siw.vetrina.repository;

import org.springframework.data.repository.CrudRepository;

import com.siw.vetrina.model.Prodotto;

public interface ProdottoRepository extends CrudRepository<Prodotto, Long> {
	public boolean existsByNome(String nome);
}
