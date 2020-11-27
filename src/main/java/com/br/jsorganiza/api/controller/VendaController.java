package com.br.jsorganiza.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
import com.br.jsorganiza.api.model.Venda;
import com.br.jsorganiza.api.service.VendaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/v1/vendas")
public class VendaController {
	
	@Autowired
	private VendaService vendasService;
	
	@GetMapping
	public ResponseEntity<List<Venda>> buscarTodasVendas() throws Exception {
		 List<Venda> vendas = vendasService.buscarTodasVendas();
		 if(vendas.isEmpty()) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Arrays.asList());
		 }
		 return ResponseEntity.status(HttpStatus.OK).body(vendas);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Venda>> buscarVenda(@PathVariable("id") Long id){
		Optional<Venda> produtoVenda = vendasService.buscarVendaPorId(id);
		if(produtoVenda == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(produtoVenda);
		}
		return ResponseEntity.status(HttpStatus.OK).body(produtoVenda);
		 
	}
	
	@PostMapping
	public ResponseEntity<Venda> criarVenda(@RequestBody Venda venda) {
		vendasService.criarVenda(venda);
		log.info("venda foi criada!!!");
		return ResponseEntity.status(HttpStatus.CREATED).body(venda);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Venda> editarVenda(@PathVariable("id") Long id, @RequestBody Venda venda) {
		Optional<Venda> vend = vendasService.buscarVendaPorId(id);
		Venda vendaNova = new Venda();
		if(vend.isPresent()) {
			vendaNova = vend.get();
			vendaNova.setNomeProduto(venda.getNomeProduto());
			vendaNova.setQuantidade(venda.getQuantidade());
			vendaNova.setValor(venda.getValor());
			vendaNova.setPagamento(venda.getPagamento());
			vendaNova.setNomeCliente(venda.getNomeCliente());
			vendaNova.setEndereco(venda.getEndereco());
			vendaNova.setTamanho(venda.getTamanho());
			vendaNova.setNomeCliente(venda.getNomeCliente());
			vendaNova.setEndereco(venda.getEndereco());
			vendaNova.setDataVenda(venda.getDataVenda());
			vendaNova.setContato(venda.getContato());
			vendasService.editarVenda(vendaNova);
			log.info("venda foi alterada!!!");
			return ResponseEntity.status(HttpStatus.OK).body(vendaNova);
		}		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Venda());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> deletarProduto(@PathVariable("id")Long id) {
		try {
			vendasService.deletarVenda(id);	
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();	
		
		}
		log.info("venda foi deletada!!!");
		return ResponseEntity.noContent().build();
	}
}
