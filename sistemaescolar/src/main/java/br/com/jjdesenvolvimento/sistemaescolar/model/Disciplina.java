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
	private List<Nota> notas;
	@ManyToOne
	@JoinColumn(name="turma_id")
	private Turma turma;
	
	public Disciplina(){
		this.professores = new ArrayList<Professor>();
		this.notas = new ArrayList<Nota>();
		this.turma = new Turma();
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

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
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
}
