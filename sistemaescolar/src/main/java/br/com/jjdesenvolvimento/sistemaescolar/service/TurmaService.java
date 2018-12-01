package br.com.jjdesenvolvimento.sistemaescolar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jjdesenvolvimento.sistemaescolar.model.Turma;
import br.com.jjdesenvolvimento.sistemaescolar.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;
	
	public void salvar(Turma turma) {
		turmaRepository.save(turma);
	}
	
	public List<Turma> buscarTodos(){
		return turmaRepository.findAll();
	}
	
	public Turma buscarPorId(Long id) {
		return turmaRepository.findById(id).get();
	}
	
	public void excluir(Long id) {
		turmaRepository.deleteById(id);
	}
	
	
}
