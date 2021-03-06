package br.com.jjdesenvolvimento.sistemaescolar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.jjdesenvolvimento.sistemaescolar.model.Secretario;
import br.com.jjdesenvolvimento.sistemaescolar.repository.SecretarioRepository;

@Service
public class SecretarioService {
	
	@Autowired
	private SecretarioRepository secretarioRepository;

	public void salvar(Secretario secretario) {
		secretario.setLogin(String.valueOf(secretario.getCpf()));
		secretario.setSenha(String.valueOf(secretario.getCpf()));
		this.secretarioRepository.save(secretario);
	}
	
	public Secretario buscarSecretarioPorCpf(Long cpf) {
		return this.secretarioRepository.findById(cpf).get();
	}
	
	public Secretario buscarPorLogin(String login) {
		return this.secretarioRepository.findByLogin(login);
	}
	
	public void excluir(Long id) {
		this.secretarioRepository.deleteById(id);
	}
	
	public List<Secretario> buscarTodos(){
		return this.secretarioRepository.findAll(new Sort("nome"));
	}
	
	public Page<Secretario> buscarTodos(int page, int size){
		return this.secretarioRepository.findAll(new PageRequest(page, size, new Sort("nome")));
	}
	
	public Page<Secretario> buscarPorNome(String nome, int page, int size){
		return this.secretarioRepository.findByNomeContaining(nome, new PageRequest(page, size, new Sort("nome")));
	
	}
}
