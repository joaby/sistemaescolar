package br.com.jjdesenvolvimento.sistemaescolar.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	
	@ManyToMany
	private List<Professor> professores;
	@OneToMany(mappedBy="disciplina")
	private List<Avaliacao> avaliacoes;
	@ManyToOne
	@JoinColumn(name="turma_id")
	private Turma turma;
	@OneToMany(mappedBy="disciplina")
	private List<Aula> aulas;
	
	public Disciplina(){
		this.professores = new ArrayList<Professor>();
		this.avaliacoes = new ArrayList<Avaliacao>();
		this.turma = new Turma();
		this.aulas = new ArrayList<Aula>();
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
    
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}
	
	public boolean isExisteProfessor() {
		if(this.professores.size() == 0) {
			return false;
		}
		return true;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}
}
