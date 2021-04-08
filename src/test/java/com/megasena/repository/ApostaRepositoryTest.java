package com.megasena.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.megasena.model.Aposta;

@ActiveProfiles("test")
@DataJpaTest
public class ApostaRepositoryTest {

	@Autowired
	private ApostaRepository repository;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("Criar uma aposta com sucesso")
	public void criarApostaComSucessoTest() {
		String apostaSeisNumeros = "06-10-15-35-54-59";
		int qtdNumeros = 6;

		Aposta aposta = new Aposta();
		aposta.setNumeros(apostaSeisNumeros);
		aposta.setQtdNumeros(qtdNumeros);
		aposta.setDataCadastro(new Date());
		aposta.setNomeLoterica("Caixas");
		aposta.setToken(UUID.randomUUID().toString());
		em.persist(aposta);

		Aposta findApostaByToken = repository.findByToken(aposta.getToken());
		assertNotNull(findApostaByToken);
		assertEquals(findApostaByToken.getToken(), aposta.getToken());
		assertEquals(findApostaByToken.getNumeros(), aposta.getNumeros());
	}

	@Test
	@DisplayName("Criar uma aposta com erro quando não tem o token do tamanho correto")
	public void criarApostaComErroTest() {
		String apostaSeisNumeros = "06-10-15-35-54-59";
		int qtdNumeros = 6;

		Aposta aposta = new Aposta();
		aposta.setNumeros(apostaSeisNumeros);
		aposta.setQtdNumeros(qtdNumeros);
		aposta.setDataCadastro(new Date());
		aposta.setToken("a92dfc6d-58de-43d0-bca7");

		ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
			em.persist(aposta);
			em.flush();
		});

		assertTrue(exception instanceof ConstraintViolationException);
	}

	@Test
	@DisplayName("Erro ao criar uma aposta com nome loterica maior que 20")
	public void criarApostaComErroDeNomeLotericaMaior20Test() {
		String apostaSeisNumeros = "06-10-15-35-54-59";
		int qtdNumeros = 6;

		Aposta aposta = new Aposta();
		aposta.setNumeros(apostaSeisNumeros);
		aposta.setQtdNumeros(qtdNumeros);
		aposta.setDataCadastro(new Date());
		aposta.setNomeLoterica("dsdhasudwhduawhduwhduwadhwudhwuadhawuddhwuadh");
		aposta.setToken(UUID.randomUUID().toString());

		ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
			em.persist(aposta);
			em.flush();
		});

		assertTrue(exception instanceof ConstraintViolationException);
	}
	
	@Test
	@DisplayName("Listando todas as apostas cadastradas")
	public void listarApostasCadastradas() {
		
		String apostaSeisNumeros = "06-10-15-35-54-59";
		int qtdNumeros = 6;

		Aposta aposta = new Aposta();
		aposta.setNumeros(apostaSeisNumeros);
		aposta.setQtdNumeros(qtdNumeros);
		aposta.setDataCadastro(new Date());
		aposta.setNomeLoterica("Caixas");
		aposta.setToken(UUID.randomUUID().toString());
		em.persist(aposta);
		
		List<Aposta> lista = repository.findAll();
		
		assertNotNull(lista);
		
		assertEquals(1, lista.size());
	}
}
