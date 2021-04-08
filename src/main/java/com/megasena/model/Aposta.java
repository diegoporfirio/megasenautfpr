	package com.megasena.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Aposta implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "O campo números é obrigatório")
	private String numeros;

	@NotNull(message = "Data da aposta é obrigatória")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@NotNull(message = "Quantidade de números é obrigatório")
	private Integer qtdNumeros;
	
	@NotNull(message = "Nome da lotérica obrigatória")
	@Size(max = 20, message = "O tamanho do nome deve ser de até 20 caracteres")
	private String nomeLoterica;

	@NotNull(message = "O token é obrigatório")
	@Size(max = 36, min = 36, message = "O tamanho do token deve ser de 36")
	private String token;

}
