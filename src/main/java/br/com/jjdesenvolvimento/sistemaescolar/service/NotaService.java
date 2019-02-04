package br.com.jjdesenvolvimento.sistemaescolar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jjdesenvolvimento.sistemaescolar.model.Nota;
import br.com.jjdesenvolvimento.sistemaescolar.repository.NotaRepository;

@Service
public class NotaService {

	@Autowired
	private NotaRepository notaRepository;
	
	public void salvar(Nota nota) {
		notaRepository.save(nota);
	}
}
