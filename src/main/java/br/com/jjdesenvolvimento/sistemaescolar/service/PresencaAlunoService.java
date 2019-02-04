package br.com.jjdesenvolvimento.sistemaescolar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jjdesenvolvimento.sistemaescolar.model.PresencaAluno;
import br.com.jjdesenvolvimento.sistemaescolar.repository.PresencaAlunoRepository;

@Service
public class PresencaAlunoService {

	@Autowired
	private PresencaAlunoRepository presencaAlunoRepository;
	
	public void salvar(PresencaAluno pa) {
		presencaAlunoRepository.save(pa);
	}
}
