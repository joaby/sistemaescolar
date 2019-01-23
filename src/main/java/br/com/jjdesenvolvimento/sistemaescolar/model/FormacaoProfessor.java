package br.com.jjdesenvolvimento.sistemaescolar.model;

public enum FormacaoProfessor {

		ENSINO_MEDIO("Ensino Médio"),
		ENSINO_PROFISSIONALIZANTE("Ensino Profissionalizante"),
		ENSINO_SUPERIOR_INCOMPLETO("Ensino Superior Incompleto"),
		ENSINO_SUPERIOR_COMPLETO("Ensino Superior Completo"),
		ESPECIALIZACAO("Especialização"),
		MESTRADO("Mestrado"),
		DOUTORADO("Doutorado");
	
	private String descricao;
	
	private FormacaoProfessor(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
