package br.com.jjdesenvolvimento.sistemaescolar.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Turma {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private int ano;
	
	private List<Aluno> alunos;
	private List<Professor> professores;
	private List<Disciplina> disciplinas;
	private List<Nota> notas;
}
