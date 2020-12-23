package com.br.jsorganiza.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.jsorganiza.api.model.Usuario;
import com.br.jsorganiza.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	public Page<Usuario> buscarTodosOsUsuarios(Pageable pageable){
		return usuarioRepository.findAll(pageable);
	}
	public Optional<Usuario> buscarUsuarioPorId(Long id){
		Usuario usuario = usuarioExiste(id);
		return Optional.of(usuario) ;
	}
	
	public Usuario criarUsuario(Usuario venda) {
		return usuarioRepository.save(venda);
	}
	public Usuario editarUsuario(Usuario venda) {
		usuarioExiste(venda.getId());
		return criarUsuario(venda);
	}
	public void deletarUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	private Usuario usuarioExiste(Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElse(null);
		if(usuario == null) {
			return null;
		}
		return usuario;
	}
}
