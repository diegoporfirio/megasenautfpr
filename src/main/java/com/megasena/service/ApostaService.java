package com.megasena.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megasena.model.Aposta;
import com.megasena.repository.ApostaRepository;

@Service
public class ApostaService {

	@Autowired
	private ApostaRepository apostaRepository;

	public void criarAposta(Aposta aposta) throws Exception {
		if (apostaRepository.findByToken(aposta.getToken()) != null) {
			throw new Exception("Aposta já cadastrada");
		}
		apostaRepository.save(aposta);
	}

	public List<Aposta> listarTodas() {
		return apostaRepository.findAll();
	}

	public List<Aposta> listarApostasPorNomeLoterica(String nomeLoterica) {
		return apostaRepository.findByNomeLoterica(nomeLoterica);
	}

}
