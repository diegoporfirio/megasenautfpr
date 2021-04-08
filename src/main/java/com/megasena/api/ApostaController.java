package com.megasena.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.megasena.model.Aposta;
import com.megasena.service.ApostaService;

@RestController
@RequestMapping("/api/v1/aposta")
public class ApostaController {

	@Autowired
	private ApostaService service;

	@PostMapping("/criar")
	public ResponseEntity<?> criar(@Valid @RequestBody Aposta aposta) {
		try {
			service.criarAposta(aposta);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/listarTodas")
	public ResponseEntity<?> listarTodas(){
		try {
			List<Aposta> apostas = service.listarTodas();
			return ResponseEntity.ok(apostas);
		}catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/listarPorNomeLoterica/{nomeLoterica}")
	public ResponseEntity<?> listarPorNomeLoterica(@PathVariable("nomeLoterica") String nomeLoterica){
		try {
			List<Aposta> apostas = service.listarApostasPorNomeLoterica(nomeLoterica);
			return ResponseEntity.ok(apostas);
		}catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
