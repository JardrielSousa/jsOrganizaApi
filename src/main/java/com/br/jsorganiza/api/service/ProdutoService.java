package com.br.jsorganiza.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.jsorganiza.api.model.Produto;
import com.br.jsorganiza.api.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Page<Produto> buscarTodosProduto(Pageable pageable){
		return produtoRepository.findAll(pageable);
	}
	public Optional<Produto> buscarProduto(Long id){
		Produto livro = produtoExiste(id);
		return Optional.of(livro) ;
	}
	
	public Produto criarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	public Produto editarProduto(Produto produto) {
		produtoExiste(produto.getId());
		return criarProduto(produto);
	}
	public void deletarProduto(Long id) {
		produtoRepository.deleteById(id);
	}
	
	private Produto produtoExiste(Long id) {
		return produtoRepository.findById(id).orElseThrow();
	}
}
