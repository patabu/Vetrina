package com.siw.vetrina.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siw.vetrina.model.Prodotto;
import com.siw.vetrina.repository.ProdottoRepository;

@Service
public class ProdottoService {
	@Autowired
	private ProdottoRepository prodottoRepository;

	@Transactional
	public void saveProdotto(Prodotto prodotto) {
		this.prodottoRepository.save(prodotto);

	}

	public Prodotto getProdotto(Long id) {
		return prodottoRepository.findById(id).get();
	}

	public List<Prodotto> getAllCategorie() {
		List<Prodotto> prodotti = new ArrayList<Prodotto>();
		for (Prodotto p : prodottoRepository.findAll())
			prodotti.add(p);
		return prodotti;
	}

	public void removeProdotto(Long id) {
		prodottoRepository.deleteById(id);
	}

	public boolean alreadyExist(Prodotto target) {
		return this.prodottoRepository.existsByNome(target.getNome());
	}
}
