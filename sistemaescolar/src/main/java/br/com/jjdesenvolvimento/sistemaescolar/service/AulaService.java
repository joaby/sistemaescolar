package br.com.jjdesenvolvimento.sistemaescolar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jjdesenvolvimento.sistemaescolar.model.Aula;
import br.com.jjdesenvolvimento.sistemaescolar.repository.AulaRepository;

@Service
public class AulaService {

	@Autowired
	private AulaRepository aulaRepository;
	
	public void salvar(Aula aula) {
		aulaRepository.save(aula);
	}
}
