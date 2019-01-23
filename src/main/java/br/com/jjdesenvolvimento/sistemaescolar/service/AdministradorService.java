package br.com.jjdesenvolvimento.sistemaescolar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jjdesenvolvimento.sistemaescolar.model.Administrador;
import br.com.jjdesenvolvimento.sistemaescolar.repository.AdministradorRepository;

@Service
public class AdministradorService {

	@Autowired
	private AdministradorRepository administradorRepository;
	
	public void salvar(Administrador administrador) {
		administrador.setLogin(administrador.getCpf());
		administrador.setSenha(administrador.getCpf());
		this.administradorRepository.save(administrador);
	}
	
	public Administrador buscarPorLogin(String login) {
		return this.administradorRepository.findByLogin(login);
	}
	
	public List<Administrador> buscarTodos(){
		return this.administradorRepository.findAll();
	}
}
