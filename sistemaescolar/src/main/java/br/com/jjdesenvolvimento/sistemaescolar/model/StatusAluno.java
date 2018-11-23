package br.com.jjdesenvolvimento.sistemaescolar.model;

public enum StatusAluno {

	ATIVO("Ativo"),
	INATIVO("Inativo"),
	TRANSFERIDO("Transferido"),
	CONCLUIDO("Concluído");
	
	StatusAluno(String descricao){
		this.descricao = descricao;
	}
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
