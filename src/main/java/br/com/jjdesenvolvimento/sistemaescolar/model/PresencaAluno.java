package br.com.jjdesenvolvimento.sistemaescolar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PresencaAluno {

	@Id
	@GeneratedValue
	private Long id;
	private Boolean presente;
	@ManyToOne
	@JoinColumn(name="aula_id")
	private Aula aula;
	@ManyToOne
	@JoinColumn(name="aluno_id")
	private Aluno aluno;
	
	public PresencaAluno() {
		aluno = new Aluno();
		setAula(new Aula());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Boolean getPresente() {
		return presente;
	}
	public void setPresente(Boolean presente) {
		this.presente = presente;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}
	
	
}
