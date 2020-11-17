package com.br.jsorganiza.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.jsorganiza.api.model.Produto;
import com.br.jsorganiza.api.model.Venda;
import com.br.jsorganiza.api.repository.ProdutoRepository;
import com.br.jsorganiza.api.repository.VendaRepository;

@Service
public class VendaService {

	private VendaRepository vendaRepository;
	
	public VendaService(VendaRepository vendaRepository) {
		this.vendaRepository = vendaRepository;
	}
	public List<Venda> buscarTodasVendas(){
		return vendaRepository.findAll();
	}
	public Optional<Venda> buscarVendaPorId(Long id){
		Venda venda = vendaExiste(id);
		return Optional.of(venda) ;
	}
	
	public Venda criarVenda(Venda venda) {
		return vendaRepository.save(venda);
	}
	public Venda editarVenda(Venda venda) {
		vendaExiste(venda.getId());
		return criarVenda(venda);
	}
	public void deletarVenda(Long id) {
		vendaRepository.deleteById(id);
	}
	
	private Venda vendaExiste(Long id) {
		Venda venda = vendaRepository.findById(id).orElse(null);
		if(venda == null) {
			return null;
		}
		return venda;
	}
}
