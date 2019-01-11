package br.com.jjdesenvolvimento.sistemaescolar.model;

public class PresencaAluno {

	private Aluno aluno;
	private Boolean presente;
	
	public PresencaAluno() {
		aluno = new Aluno();
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
	
	
}
