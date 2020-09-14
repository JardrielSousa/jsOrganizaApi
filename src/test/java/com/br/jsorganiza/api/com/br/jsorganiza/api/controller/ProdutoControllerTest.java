package com.br.jsorganiza.api.com.br.jsorganiza.api.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.br.jsorganiza.api.controller.ProdutoController;
import com.br.jsorganiza.api.model.Produto;
import com.br.jsorganiza.api.service.ProdutoService;

import io.restassured.http.ContentType;

@WebMvcTest
class ProdutoControllerTest {
	@Autowired
	private ProdutoController produtoController;
	
	@MockBean
	private ProdutoService produtoService;
	
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.produtoController);
	}
	
	@Test
	void test01deveRetornarSucesso_QuandoBuscarProdutoPorId() {
		when(this.produtoService.buscarProduto(1L)).thenReturn(produtoIdUm());
		given()
			.accept(ContentType.JSON)
		.when()
			.get("https://localhost:8080/v1/produto/{id}",1L)
		.then().statusCode(HttpStatus.OK.value());
		
	}

	@Test
	void test02deveRetornarNaoEncontrado_QuandoBuscarProdutoPorId() {
		when(this.produtoService.buscarProduto(5L)).thenReturn(null);
		given()
			.accept(ContentType.JSON)
		.when()
			.get("https://localhost:8080/v1/produto/{id}",5L)
		.then().statusCode(HttpStatus.NOT_FOUND.value());
		
	}
	
	@Test
	void test03deveRetornarSucesso_QuandoCriarProduto() {
		when(this.produtoService.criarProduto(new Produto(1L, "produto criado", 99.0, 1,new Date()))).thenReturn(new Produto(1L, "produto criado", 99.0, 1,new Date()));
		given()
			.accept(ContentType.JSON)
			.body(new Produto(1L, "produto criado", 99.0, 1,new Date()))
			.contentType(ContentType.JSON)
		.when()
			.post("https://localhost:8080/v1/produto/")
		.then().statusCode(HttpStatus.CREATED.value());
		
	}
	
	/*
	 * @Test void test04deveRetornarSucesso_QuandoCriarEditarProduto() {
	 * when(this.produtoService.editarProduto(produtoEditado())).thenReturn(
	 * produtoEditado()); given() .accept(ContentType.JSON) .body(produtoEditado())
	 * .contentType(ContentType.JSON) .when()
	 * .put("https://localhost:8080/v1/produto/{id}",1L , produtoEditado())
	 * .then().statusCode(HttpStatus.OK.value());
	 * 
	 * }
	 */
	
	private List<Object> todosOsProdutos() {
		return Arrays.asList(produtoIdUm(),new Produto(2L,"teste 2",20.0,1,new Date()));
	}
	
	private Optional<Produto> produtoIdUm() {
		return Optional.ofNullable(new Produto(1L,"teste",20.0,1,new Date()));
	}
	
	private Produto produtoEditado() {
		Produto produto = new Produto(1L, "produto editado", 100.0, 2,new Date());
		return produto ;
	}
	
}
