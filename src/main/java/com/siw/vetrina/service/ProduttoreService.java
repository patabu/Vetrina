package com.siw.vetrina.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siw.vetrina.model.Produttore;
import com.siw.vetrina.repository.ProduttoreRepository;

@Service
public class ProduttoreService {
	@Autowired
	private ProduttoreRepository produttoreRepository;

	@Transactional
	public void saveProduttore(Produttore produttore) {
		this.produttoreRepository.save(produttore);

	}

	public Produttore getProduttore(Long id) {
		return produttoreRepository.findById(id).get();
	}

	public List<Produttore> getAllCategorie() {
		List<Produttore> produttori = new ArrayList<Produttore>();
		for (Produttore p : produttoreRepository.findAll())
			produttori.add(p);
		return produttori;
	}

	public void removeProduttore(Long id) {
		produttoreRepository.deleteById(id);
	}

	public boolean alreadyExist(Produttore target) {
		return this.produttoreRepository.existsByNomeAndSede(target.getNome(), target.getSede());
	}

}
