package br.com.jjdesenvolvimento.sistemaescolar.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.jjdesenvolvimento.sistemaescolar.model.TipoUsuario;

public class UsuarioSistema extends User{

	private static final long serialVersionUID = 1L;
	private String nome;
	private TipoUsuario tipo;
	
	public UsuarioSistema(String nome, TipoUsuario tipo, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.nome = nome;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

}
