package com.megasena.service.db;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megasena.model.Aposta;
import com.megasena.repository.ApostaRepository;

@Service
public class DBService {

	@Autowired
	private ApostaRepository apostaRepository;
	
	public void instantiateTestDatabase() {
		String apostaSeisNumeros = "06-10-15-35-54-59";
		int qtdNumeros = 6;

		Aposta aposta = new Aposta();
		aposta.setNumeros(apostaSeisNumeros);
		aposta.setQtdNumeros(qtdNumeros);
		aposta.setDataCadastro(new Date());
		aposta.setNomeLoterica("Caixas");
		aposta.setToken(UUID.randomUUID().toString());
		apostaRepository.save(aposta);
		
		String aposta2SeisNumeros = "06-10-15-35-53-54";
		int qtdNumeros2 = 6;

		Aposta aposta2 = new Aposta();
		aposta2.setNumeros(aposta2SeisNumeros);
		aposta2.setQtdNumeros(qtdNumeros2);
		aposta2.setDataCadastro(new Date());
		aposta2.setNomeLoterica("Caixas");
		aposta2.setToken(UUID.randomUUID().toString());
		apostaRepository.save(aposta2);
		
		Aposta aposta3 = new Aposta();
		aposta3.setNumeros(aposta2SeisNumeros);
		aposta3.setQtdNumeros(qtdNumeros2);
		aposta3.setDataCadastro(new Date());
		aposta3.setNomeLoterica("Caixas2");
		aposta3.setToken(UUID.randomUUID().toString());
		apostaRepository.save(aposta3);
	}
}
