package com.br.jsorganiza.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@NotNull(message = "Nome é obrigatório")
	private String nome;
	@PositiveOrZero(message = "São permitidos apenas números positivos e zero")
	private Double valor;
	@PositiveOrZero(message = "São permitidos apenas números positivos e zero")
	private Double valorVenda;
	@PositiveOrZero(message = "São permitidos apenas números positivos e zero")
	private int quantidade;
	@NotNull(message = "Data da compra é obrigatório")
	private Date dataCompra;

	public Produto(Long id) {
		id = Id;
	}

	
	
}
