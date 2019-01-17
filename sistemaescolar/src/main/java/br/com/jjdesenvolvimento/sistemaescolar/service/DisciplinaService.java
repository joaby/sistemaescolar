package br.com.jjdesenvolvimento.sistemaescolar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jjdesenvolvimento.sistemaescolar.model.Aluno;
import br.com.jjdesenvolvimento.sistemaescolar.model.Disciplina;
import br.com.jjdesenvolvimento.sistemaescolar.model.PresencaAluno;
import br.com.jjdesenvolvimento.sistemaescolar.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	public void salvar(Disciplina disciplina) {
		disciplinaRepository.save(disciplina);
	}
	
	public Disciplina buscarPorId(Long id) {
		return disciplinaRepository.findById(id).get();
	}
	
	public List<PresencaAluno> mudarParaPresencaAluno(List<Aluno> alunos){
		List<PresencaAluno> presencaAlunos = new ArrayList<PresencaAluno>();
		for (Aluno aluno : alunos) {
			PresencaAluno pa = new PresencaAluno();
			pa.setAluno(aluno);
			pa.setPresente(true);
			presencaAlunos.add(pa);
		}
		return presencaAlunos;
	}
	
	public List<Disciplina> buscarDisciplinasPorAno(List<Disciplina> disciplinas, int ano){
		List<Disciplina> disciplinasAnoLetivo = new ArrayList<Disciplina>();
		for (Disciplina disciplina : disciplinas) {
			if(disciplina.getTurma().getAno() == ano) {
				disciplinasAnoLetivo.add(disciplina);
			}
		}
		return disciplinasAnoLetivo;
	}
}
