package br.com.jjdesenvolvimento.sistemaescolar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Nota {
	
	@Id
	@GeneratedValue
	private Long id;
	private float nota;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	
	

}
