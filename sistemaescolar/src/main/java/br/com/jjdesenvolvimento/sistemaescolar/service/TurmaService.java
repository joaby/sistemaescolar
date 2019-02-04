package br.com.jjdesenvolvimento.sistemaescolar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jjdesenvolvimento.sistemaescolar.model.Turma;
import br.com.jjdesenvolvimento.sistemaescolar.model.TurnoTurma;
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
	
	public List<Turma> filtrarTurmaPorAnoTurno(List<Turma> turmas, int ano, TurnoTurma turno){
		List<Turma> turmasFiltrada = new ArrayList<Turma>();
		for (int i = 0; i < turmas.size(); i++) {
			if (turmas.get(i).getAno() == ano && turmas.get(i).getTurno().equals(turno)) {
				turmasFiltrada.add(turmas.get(i));
			}
		}
		return turmasFiltrada;
	}
	
	public List<Turma> filtrarTurmaPorAno(List<Turma> turmas, int ano){
		List<Turma> turmasFiltrada = new ArrayList<Turma>();
		for (int i = 0; i < turmas.size(); i++) {
			if (turmas.get(i).getAno() == ano) {
				turmasFiltrada.add(turmas.get(i));
			}
		}
		return turmasFiltrada;
	}
	
	public void excluir(Long id) {
		turmaRepository.deleteById(id);
	}
	
	
}
