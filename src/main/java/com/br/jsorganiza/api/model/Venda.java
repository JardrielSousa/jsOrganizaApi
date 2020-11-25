package com.br.jsorganiza.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.br.jsorganiza.api.enums.FormaDePagamento;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@NotNull(message = "Data da compra é obrigatório")
	private Date dataVenda;
	@NotNull(message = "Nome é obrigatório")
	private String nomeProduto;
	@NotNull(message = "Quantidade é obrigatório")
	private int quantidade;
	@NotNull(message = "valor é obrigatório")
	private double valor;
	@NotNull(message = "Forma de pagamento é obrigatório")
	private FormaDePagamento pagamento;
	@NotNull(message = "Nome do cliente é obrigatório")
	private String nomeCliente;
	@NotNull(message = "endereço é obrigatório")
	private String endereco;
	@NotNull(message = "contato é obrigatório")
	private String contato;
	@NotNull(message = "Tamanho é obrigatório")
	private String tamanho;
}
