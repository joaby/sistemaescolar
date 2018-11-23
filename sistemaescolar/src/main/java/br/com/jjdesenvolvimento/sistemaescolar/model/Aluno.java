package br.com.jjdesenvolvimento.sistemaescolar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Aluno {
	
	@Id
	private Long id;
	private String nome;
	private Date dataNascimento;
	private char sexo;
	private StatusAluno status;
	
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
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public StatusAluno getStatus() {
		return status;
	}
	public void setStatus(StatusAluno status) {
		this.status = status;
	}
	
	
	

}
