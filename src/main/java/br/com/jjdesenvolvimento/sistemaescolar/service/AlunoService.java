package br.com.jjdesenvolvimento.sistemaescolar.service;

import java.util.List;
import java.util.Optional;

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
	
	public Aluno buscarPorId(Long id){
		Aluno a = alunoRepository.findById(id).get();
		return a;
	}
	
	public Optional<Aluno> buscarPorMatricula(String matricula){
		return alunoRepository.findByMatricula(matricula);
	}
	
	public void excluir(Long id){
		alunoRepository.deleteById(id);
	}
	
}
