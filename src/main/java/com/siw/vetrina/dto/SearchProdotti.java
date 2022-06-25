package com.siw.vetrina.dto;

import com.siw.vetrina.model.Categoria;
import com.siw.vetrina.model.Produttore;

public class SearchProdotti {  

	private String nomeProdotto;   
	private Categoria categoria;
	private Produttore produttore;
	
	public SearchProdotti() {
		this.categoria = new Categoria();
		this.produttore = new Produttore();
		this.categoria.setId(0);
		this.produttore.setId(0);
	} 
	
	public SearchProdotti(String nomeProdotto, Categoria categoria, Produttore produttore) {
		this.nomeProdotto = nomeProdotto;
		this.categoria = categoria;
		this.produttore = produttore;
	}
	public String getNomeProdotto() {
		return nomeProdotto;
	}
	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Produttore getProduttore() {
		return produttore;
	}
	public void setProduttore(Produttore produttore) {
		this.produttore = produttore;
	}	  
}
