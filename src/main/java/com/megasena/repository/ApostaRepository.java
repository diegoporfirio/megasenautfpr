package com.megasena.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megasena.model.Aposta;

public interface ApostaRepository extends JpaRepository<Aposta, Long> {

	Aposta findByToken(String token);

	List<Aposta> findByNomeLoterica(String nomeLoterica);

}
