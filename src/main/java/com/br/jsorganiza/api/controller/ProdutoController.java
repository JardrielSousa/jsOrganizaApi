package com.br.jsorganiza.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<Page<Produto>> buscarTodosProdutos(Pageable pageable) throws Exception {
		 Page<Produto> produtos = produtoService.buscarTodosProduto(pageable);
		 return ResponseEntity.status(HttpStatus.OK).body(produtos);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Produto>> buscarProduto(@PathVariable("id") Long id){
		Optional<Produto> produtoResult = produtoService.buscarProduto(id);
		if(produtoResult.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(produtoResult);
		}
		return ResponseEntity.status(HttpStatus.OK).body(produtoResult);
		 
	}
	
	@PostMapping
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
		produtoService.criarProduto(produto);
		log.info("Produto foi criado!!!");
		return ResponseEntity.status(HttpStatus.CREATED).body(produto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> editarProduto(@PathVariable("id") Long id, @RequestBody Produto produto) {
		Optional<Produto> prod = produtoService.buscarProduto(id);
		Produto produtoNovo = new Produto();
		if(prod.isPresent()) {
			produtoNovo = setarAlteracoesProduto(produto, prod);
			log.info("Produto foi alterado!!!");
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
		log.info("Produto foi deletado!!!");
		return ResponseEntity.noContent().build();
	}
	
	private Produto setarAlteracoesProduto(Produto produto, Optional<Produto> prod) {
		Produto produtoNovo;
		produtoNovo = prod.get();
		produtoNovo.setNome(produto.getNome());
		produtoNovo.setQuantidade(produto.getQuantidade());
		produtoNovo.setValor(produto.getValor());
		produtoNovo.setValorVenda(produto.getValorVenda());
		produtoNovo.setDataCompra(produto.getDataCompra());
		produtoService.editarProduto(produtoNovo);
		return produtoNovo;
	}
	
}
