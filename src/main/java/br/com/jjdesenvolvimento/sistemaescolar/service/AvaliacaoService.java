package br.com.jjdesenvolvimento.sistemaescolar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jjdesenvolvimento.sistemaescolar.model.Avaliacao;
import br.com.jjdesenvolvimento.sistemaescolar.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	public void salvar(Avaliacao avaliacao) {
		avaliacaoRepository.save(avaliacao);
	}
	
	public Avaliacao buscarPorId(Long id) {
		return avaliacaoRepository.findById(id).get();
	}
}
