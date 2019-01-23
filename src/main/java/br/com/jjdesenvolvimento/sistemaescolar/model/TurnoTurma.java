package br.com.jjdesenvolvimento.sistemaescolar.model;

public enum TurnoTurma {

	MANHA("Manhã"),
	TARDE("Tarde"),
	NOITE("Noite");
	
	private String descricao;

	private TurnoTurma(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
