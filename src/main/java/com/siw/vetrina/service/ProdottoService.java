package com.siw.vetrina.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.siw.vetrina.dto.SearchProdotti;
import com.siw.vetrina.model.Categoria;
import com.siw.vetrina.model.Prodotto;
import com.siw.vetrina.repository.ProdottoRepository;

@Service
public class ProdottoService {
	@Autowired
	private ProdottoRepository prodottoRepository;
	@Autowired
	private ProduttoreService produttoreService;
	@Autowired
	private CategoriaService categoriaService;

	@Transactional
	public void saveProdotto(Prodotto prodotto) {
		this.prodottoRepository.save(prodotto);

	}

	public Prodotto getProdotto(Long id) {
		return prodottoRepository.findById(id).get();
	}

	public List<Prodotto> getAllProdotti() {
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
	
	public List<Prodotto> getAllProdottiByCategoria(Categoria categoria) {
		List<Prodotto> prodotti = this.prodottoRepository.findByCategoria(categoria).get();
		for (Prodotto p : prodotti)
			System.out.println("C: " + p.getCategoria() + " N: " + p.getNome());
		return prodotti; 
	}

	public List<Prodotto> searchProdotti(SearchProdotti search) {
		
		if (search.getCategoria() != null) { 
			System.out.println("Categoria: " + search.getCategoria().getNome());
		}
		if (search.getProduttore() != null) {
			System.out.println("Produttore: " + search.getProduttore().getNome());
		}
		if (!search.getNomeProdotto().isEmpty()) {
			System.out.println("Prodotto: " + search.getNomeProdotto());
		}  
		
		if (search.getCategoria() != null) {
			if (!search.getNomeProdotto().isEmpty()) {
				if (search.getProduttore() != null) return this.prodottoRepository.findByNomeStartingWithIgnoreCaseAndCategoriaAndProduttore(search.getNomeProdotto(), search.getCategoria(), search.getProduttore()).get(); 
				return this.prodottoRepository.findByNomeStartingWithIgnoreCaseAndCategoria(search.getNomeProdotto(), search.getCategoria()).get(); 
			} else {
				if (search.getProduttore() != null) return this.prodottoRepository.findByCategoriaAndProduttore(search.getCategoria(), search.getProduttore()).get(); 
				return this.prodottoRepository.findByCategoria(search.getCategoria()).get(); 
			}
		} else {
			if (!search.getNomeProdotto().isEmpty()) {
				if (search.getProduttore() != null) return this.prodottoRepository.findByNomeStartingWithIgnoreCaseAndProduttore(search.getNomeProdotto(), search.getProduttore()).get(); 
				return this.prodottoRepository.findByNomeStartingWithIgnoreCase(search.getNomeProdotto()).get(); 
			} else if (search.getProduttore() != null) return this.prodottoRepository.findByProduttore(search.getProduttore()).get(); 
		}
		return this.getAllProdotti(); 
	}
	
	public void getHomeData(Model model, List<Prodotto> prodotti) {
		model.addAttribute("categorie", this.categoriaService.getAllCategorie());
		model.addAttribute("produttori", this.produttoreService.getAllProduttori());
		model.addAttribute("prodotti", prodotti);
		model.addAttribute("SearchProdotti", new SearchProdotti()); 
	}
	
}
