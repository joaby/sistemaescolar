package br.com.jjdesenvolvimento.sistemaescolar.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Turma {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private int ano;
	@Enumerated(EnumType.STRING)
	private TurnoTurma turno; 
	
	@ManyToOne
	@JoinColumn(name="escola_id")
	private Escola escola;
	@OneToMany(mappedBy="turma")
	private List<Disciplina> disciplinas;
	@ManyToMany(mappedBy="turmas")
	private List<Aluno> alunos;
	
	public Turma(){
		this.escola = new Escola();
		this.disciplinas = new ArrayList<Disciplina>();
		this.alunos = new ArrayList<Aluno>();
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

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public TurnoTurma getTurno() {
		return turno;
	}

	public void setTurno(TurnoTurma turno) {
		this.turno = turno;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}
	

}
