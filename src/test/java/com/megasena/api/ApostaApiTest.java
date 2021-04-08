package com.megasena.api;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megasena.model.Aposta;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ApostaApiTest {

	static String APOSTA_API_CRIAR = "/api/v1/aposta/criar";
	
	static String APOSTA_API_LISTAR_TODAS = "/api/v1/aposta/listarTodas";

	static String APOSTA_API_LISTAR_POR_NOME_LOTERICA = "/api/v1/aposta/listarPorNomeLoterica";
	
	@Autowired
	private MockMvc mvc;

	@Test
	@DisplayName("Criar uma aposta com sucesso via API")
	public void criarApostaComSucessoAPITest() throws Exception {

		String apostaSeisNumeros = "06-10-15-35-54-59";
		int qtdNumeros = 6;

		Aposta aposta = new Aposta();
		aposta.setNumeros(apostaSeisNumeros);
		aposta.setQtdNumeros(qtdNumeros);
		aposta.setDataCadastro(new Date());
		aposta.setNomeLoterica("Caixas Teste");
		aposta.setToken(UUID.randomUUID().toString());

		String json = new ObjectMapper().writeValueAsString(aposta);

		mvc.perform(post(APOSTA_API_CRIAR).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Criar uma aposta com erro via API")
	public void criarApostaComErroAPITest() throws Exception {

		int qtdNumeros = 6;

		Aposta aposta = new Aposta();
		aposta.setNumeros("");
		aposta.setQtdNumeros(qtdNumeros);
		aposta.setDataCadastro(new Date());
		aposta.setToken("");

		String json = new ObjectMapper().writeValueAsString(aposta);
		
		
		mvc.perform(MockMvcRequestBuilders.post(APOSTA_API_CRIAR)
			      .content(json)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers.status().isBadRequest())
			      //.andExpect(MockMvcResultMatchers.jsonPath("errors",Is.is(new StringBuilder().append("[").append("\"").append("token: O tamanho do token deve ser de 36").append("\"").append("]"))))
			      .andExpect(MockMvcResultMatchers.content()
			      .contentType(MediaType.APPLICATION_JSON));
		
	}

	@Test
	@DisplayName("Erro ao criar uma aposta com nome loterica maior que 20")
	public void criarApostaComErroDeNomeLotericaMaior20APITest() throws Exception {

		String apostaSeisNumeros = "06-10-15-35-54-59";
		int qtdNumeros = 6;

		Aposta aposta = new Aposta();
		aposta.setNumeros(apostaSeisNumeros);
		aposta.setQtdNumeros(qtdNumeros);
		aposta.setDataCadastro(new Date());
		aposta.setNomeLoterica("ahfbdgstaredgshdtrgawushf");
		aposta.setToken(UUID.randomUUID().toString());

		String json = new ObjectMapper().writeValueAsString(aposta);

		mvc.perform(post(APOSTA_API_CRIAR).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(status().isBadRequest());
	}
	
	@Test
	@DisplayName("Listar todas as apostas cadastradas")
	public void ListarTodasAsApostasCadastradasAPITest() throws Exception {

		 
	        mvc.perform(get(APOSTA_API_LISTAR_TODAS))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$", hasSize(4)));
	 
	 }
	
	@Test
	@DisplayName("Listar todas as apostas cadastradas por nome da loterica")
	public void ListarTodasAsApostasPorNomeLotericaAPITest() throws Exception {

		 
	        mvc.perform(get(APOSTA_API_LISTAR_POR_NOME_LOTERICA + "/Caixas"))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$", hasSize(2)));
	        
	        mvc.perform(get(APOSTA_API_LISTAR_POR_NOME_LOTERICA + "/Caixas2"))
		            .andExpect(status().isOk())
		            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		            .andExpect(jsonPath("$", hasSize(1)));
	        
	 }
	
}
