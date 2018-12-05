package br.com.jjdesenvolvimento.sistemaescolar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jjdesenvolvimento.sistemaescolar.model.Aluno;
import br.com.jjdesenvolvimento.sistemaescolar.repository.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;

	public void salvar(Aluno aluno) {
		alunoRepository.save(aluno);
	}
	
	public List<Aluno> buscarTodos() {
		return alunoRepository.findAll();
	}
	
	public Aluno buscarPorId(Long matricula) {
		return alunoRepository.findById(matricula).get();	
		
	}
	
	public void excluir(Long matricula){
		alunoRepository.deleteById(matricula);
	}
	
}
