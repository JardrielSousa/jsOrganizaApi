package com.br.jsorganiza.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.jsorganiza.api.model.Produto;
import com.br.jsorganiza.api.service.ProdutoService;

@RestController
@RequestMapping("/v1/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> buscarTodosProdutos() throws Exception {
		 List<Produto> produtos = produtoService.buscarTodosProduto();
		 if(produtos.isEmpty()) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Arrays.asList());
		 }
		 return ResponseEntity.status(HttpStatus.OK).body(produtos);
	}
	
	@PostMapping
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
		produtoService.criarProduto(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> editarProduto(@PathVariable("id") Long id, @RequestBody Produto produto) {
		Optional<Produto> prod = produtoService.buscarProduto(id);
		Produto produtoNovo = new Produto();
		if(prod.isPresent()) {
			produtoNovo = prod.get();
			produtoNovo.setNome(produto.getNome());
			produtoNovo.setQuantidade(produto.getQuantidade());
			produtoNovo.setValor(produto.getValor());
			produtoService.editarProduto(produtoNovo);
			return ResponseEntity.status(HttpStatus.OK).body(produtoNovo);
		}		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Produto());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> deletarProduto(@PathVariable("id")Long id) {
		try {
			produtoService.deletarProduto(id);	
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();	
		
		}
		return ResponseEntity.noContent().build();
	}
}
