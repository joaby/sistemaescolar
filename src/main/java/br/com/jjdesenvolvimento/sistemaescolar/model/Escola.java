package br.com.jjdesenvolvimento.sistemaescolar.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Escola {

	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	@Size(max=50)
	private String nome;
	
	@OneToMany(mappedBy="escola")
	private List<Turma> turmas;
	@OneToMany(mappedBy="escola")
	private List<Secretario> secretarios;
	
	public Escola(){
		this.turmas = new ArrayList<Turma>();
		this.secretarios = new ArrayList<Secretario>();
	}

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

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<Secretario> getSecretarios() {
		return secretarios;
	}

	public void setSecretarios(List<Secretario> secretarios) {
		this.secretarios = secretarios;
	}
	
}
