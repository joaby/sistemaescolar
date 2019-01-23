package br.com.jjdesenvolvimento.sistemaescolar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Secretario {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true)
	@NotEmpty
	@Size(max=11, min=11)
	private String cpf;
	
	@NotEmpty
	@Size(max=50)
	private String nome;
	
	@Column(unique=true)
	private String login;
	
	private String senha;
	
	@ManyToOne
	@JoinColumn(name="escola_id")
	private Escola escola;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Escola getEscola() {
		return escola;
	}
	public void setEscola(Escola escola) {
		this.escola = escola;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}	

}
