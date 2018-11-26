package br.com.jjdesenvolvimento.sistemaescolar.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	
	private List<Nota> notas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
