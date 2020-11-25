package com.br.jsorganiza.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.br.jsorganiza.api.enums.FormaDePagamento;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@NotNull(message = "Nome é obrigatório")
	private String nome;
	@PositiveOrZero(message = "São permitidos apenas números positivos e zero")
	private Double valor;
	@PositiveOrZero(message = "São permitidos apenas números positivos e zero")
	private int quantidade;
	@NotNull(message = "Data da compra é obrigatório")
	private Date dataCompra;
	public Produto() {
		
	}
	
	
	public Produto(Long id, String nome, Double valor, int quantidade,Date dataCompra) {
		super();
		Id = id;
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.dataCompra = dataCompra;
		}

	public Produto(Long id) {
		id = Id;
	}	
	
}
