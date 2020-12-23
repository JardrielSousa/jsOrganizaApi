package com.br.jsorganiza.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 5 , message = "precisa inserir um nome maior que 5 caracteries")
	private String nome;
	@NotBlank
	@Email(message = "precisa inserir um email válido")
	@Size(min = 5 , message = "precisa inserir um email maior que 5 caracteries")
	private String email;
	@NotBlank
	@NotNull(message = "senha é obrigatório")
	@Size(min = 8 , message = "precisa inserir uma senha maior que 8 caracteries")
	private String senha;
	@NotNull(message = "Data da nascimento é obrigatório")
	@Column(name = "data_nascimento")
	private Date dataNascimento;
}
