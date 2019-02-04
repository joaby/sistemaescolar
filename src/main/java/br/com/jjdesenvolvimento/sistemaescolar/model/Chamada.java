package br.com.jjdesenvolvimento.sistemaescolar.model;

import java.util.ArrayList;
import java.util.List;

public class Chamada {

	private List<PresencaAluno> presencaAlunos;
	
	public Chamada() {
		this.presencaAlunos = new ArrayList<PresencaAluno>();
	}

	public List<PresencaAluno> getPresencaAlunos() {
		return presencaAlunos;
	}

	public void setPresencaAlunos(List<PresencaAluno> presencaAlunos) {
		this.presencaAlunos = presencaAlunos;
	}
	
	
}
