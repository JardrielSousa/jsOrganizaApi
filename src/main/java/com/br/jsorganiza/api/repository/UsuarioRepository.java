package com.br.jsorganiza.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.jsorganiza.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
