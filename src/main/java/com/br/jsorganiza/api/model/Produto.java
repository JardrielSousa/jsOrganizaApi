package com.br.jsorganiza.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	@GeneratedValue
	private Long Id;
	private String nome;
	private Double valor;
	private int quantidade;
	
	public Produto() {
		
	}
	
	
	public Produto(Long id, String nome, Double valor, int quantidade) {
		super();
		Id = id;
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
	}


	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
}
