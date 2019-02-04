package br.com.jjdesenvolvimento.sistemaescolar.model;

public enum TipoUsuario {
	
	ALUNO("Aluno"),
	PROFESSOR("Professor"),
	SECRETARIO("Secretário"),
	ADMINISTRADOR("Administrador");
	
	private String descricao;
	
	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
