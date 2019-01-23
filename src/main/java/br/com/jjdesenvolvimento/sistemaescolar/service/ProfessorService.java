package br.com.jjdesenvolvimento.sistemaescolar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jjdesenvolvimento.sistemaescolar.model.Professor;
import br.com.jjdesenvolvimento.sistemaescolar.repository.ProfessorRepository;

@Service
public class ProfessorService{
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	public void salvar(Professor professor) {
		professorRepository.save(professor);
	}
	
	public List<Professor> buscarTodos(){
		return professorRepository.findAll();
	}
	
	public Professor buscarPorCpf(String cpf) {
		return professorRepository.findByCpf(cpf);
	}
	
	public Professor buscarPorLogin(String login) {
		return professorRepository.findByLogin(login);
	}
	
	public void excluir(Long id) {
		professorRepository.deleteById(id);
	}

}
