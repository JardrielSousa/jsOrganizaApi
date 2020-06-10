package com.br.jsorganiza.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.jsorganiza.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
