package br.com.jjdesenvolvimento.sistemaescolar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Nota {
	
	@Id
	@GeneratedValue
	private Long id;
	private float nota;
	
	@ManyToOne
	@JoinColumn(name="aluno_id")
	private Aluno aluno;
	@ManyToOne
	@JoinColumn(name="avaliacao_id")
	private Avaliacao avaliacao;
	
	
	public Nota(){
		this.aluno = new Aluno();
		this.avaliacao = new Avaliacao();
	}
	
	public Nota(float nota, Aluno aluno, Avaliacao avaliacao) {
		this.nota = nota;
		this.aluno = aluno;
		this.avaliacao = avaliacao;
	}
	
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
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	
}
