package com.br.jsorganiza.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.jsorganiza.api.model.Produto;
import com.br.jsorganiza.api.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private ProdutoRepository produtoRepository;
	
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	public List<Produto> buscarTodosProduto(){
		return produtoRepository.findAll();
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
		Produto produto = produtoRepository.findById(id).orElse(null);
		if(produto == null) {
			return null;
		}
		return produto;
	}
}
