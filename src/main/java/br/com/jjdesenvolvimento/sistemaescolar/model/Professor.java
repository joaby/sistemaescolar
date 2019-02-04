package br.com.jjdesenvolvimento.sistemaescolar.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Professor{
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique=true)
	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	@Enumerated(EnumType.STRING)
	private FormacaoProfessor formacao;
	private String curso;
	@Column(unique=true)
	private String login;
	private String senha;
	
	@ManyToMany(mappedBy="professores")
	private List<Disciplina> disciplinas;
	
	public Professor(){
		this.setDisciplinas(new ArrayList<Disciplina>());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public FormacaoProfessor getFormacao() {
		return formacao;
	}
	public void setFormacao(FormacaoProfessor formacao) {
		this.formacao = formacao;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
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
