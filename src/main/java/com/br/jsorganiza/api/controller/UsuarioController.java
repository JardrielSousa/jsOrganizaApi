package com.br.jsorganiza.api.controller;

import java.util.Optional;

import javax.validation.Valid;

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
import com.br.jsorganiza.api.model.Usuario;
import com.br.jsorganiza.api.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<Page<Usuario>> buscarTodasVendas(Pageable pageable) throws Exception {
		
		 return ResponseEntity.ok(usuarioService.buscarTodosOsUsuarios(pageable));
	}
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Usuario>> buscarVenda(@PathVariable("id") Long id){
		Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(id);
		if(usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuario);
		}
		return ResponseEntity.ok(usuario);
		 
	}
	
	@PostMapping
	public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
		usuarioService.criarUsuario(usuario);
		log.info("usuario foi criado!!!");
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> editarVenda(@Valid @PathVariable("id") Long id, @RequestBody Usuario usuario) {
		Optional<Usuario> user = usuarioService.buscarUsuarioPorId(id);
		Usuario novoUsuario = new Usuario();
		if(estaPresente(user)) {
			novoUsuario = setarEdicoesAoUsuario(usuario, user);
			usuarioService.editarUsuario(novoUsuario);
			log.info("usuário foi alterado!!!");
			return ResponseEntity.status(HttpStatus.OK).body(novoUsuario);
		}		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Usuario());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> deletarProduto(@PathVariable("id")Long id) {
		try {
			usuarioService.deletarUsuario(id);	
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();	
		
		}
		log.info("Usuario foi deletado!!!");
		return ResponseEntity.noContent().build();
	}
	
	private boolean estaPresente(Optional<Usuario> user) {
		return user.isPresent();
	}
	private Usuario setarEdicoesAoUsuario(Usuario usuario, Optional<Usuario> user) {
		Usuario novoUsuario;
		novoUsuario = user.get();
		novoUsuario.setNome(usuario.getNome());
		novoUsuario.setEmail(usuario.getEmail());
		novoUsuario.setSenha(usuario.getSenha());
		novoUsuario.setDataNascimento(usuario.getDataNascimento());
		return novoUsuario;
	}
}
