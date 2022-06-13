package com.siw.vetrina.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Produttore {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String sede;
	@OneToMany(mappedBy = "produttore", cascade = CascadeType.ALL)
	private List<Prodotto> prodotti;

	public Produttore() {
	}

	public Produttore(long id, @NotBlank String nome, @NotBlank String sede, List<Prodotto> prodotti) {
		super();
		this.id = id;
		this.nome = nome;
		this.sede = sede;
		this.prodotti = prodotti;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

}
