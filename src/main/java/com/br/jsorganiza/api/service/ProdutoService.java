package com.br.jsorganiza.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.jsorganiza.api.model.Produto;
import com.br.jsorganiza.api.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> buscarTodosProduto(){
		return produtoRepository.findAll();
	}
	public Optional<Produto> buscarProduto(Long id){
		return produtoRepository.findById(id);
	}
	public Produto criarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	public Produto editarProduto(Produto produto) {
		return criarProduto(produto);
	}
	public void deletarProduto(Long id) {
		produtoRepository.deleteById(id);
	}
}
