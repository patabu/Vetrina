package com.siw.vetrina.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.siw.vetrina.model.Categoria;
import com.siw.vetrina.model.Prodotto;
import com.siw.vetrina.model.Produttore;

public interface ProdottoRepository extends CrudRepository<Prodotto, Long> {
	
	public boolean existsByNome(String nome);
	
	public Optional<List<Prodotto>> findByCategoria(Categoria categoria);
	
	public Optional<List<Prodotto>> findByProduttore(Produttore produttore);
	
	public Optional<List<Prodotto>> findByNomeStartingWithIgnoreCase(String nome);
	
	public Optional<List<Prodotto>> findByNomeStartingWithIgnoreCaseAndCategoria(String nome, Categoria categoria);
	
	public Optional<List<Prodotto>> findByNomeStartingWithIgnoreCaseAndProduttore(String nome, Produttore produttore);
	
	public Optional<List<Prodotto>> findByCategoriaAndProduttore(Categoria categoria, Produttore produttore);
	
	public Optional<List<Prodotto>> findByNomeStartingWithIgnoreCaseAndCategoriaAndProduttore(String nome, Categoria categoria, Produttore produttore);
	
	
	
	
}
