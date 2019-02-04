package br.com.jjdesenvolvimento.sistemaescolar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.jjdesenvolvimento.sistemaescolar.model.Escola;
import br.com.jjdesenvolvimento.sistemaescolar.repository.EscolaRepository;

@Service
public class EscolaService {

	@Autowired
	private EscolaRepository escolaRepository;
	
	public void salvar(Escola escola) {
		escolaRepository.save(escola);
	}
	
	public List<Escola> buscarTodas(){
		return escolaRepository.findAll(new Sort("nome"));
	}
	
	public Optional<Escola> buscarPorId(Long id) {
		return escolaRepository.findById(id);
	}
	
	public Page<Escola> buscarTodas(int page, int size){
		return escolaRepository.findAll(new PageRequest(page, size, new Sort("nome")));
	}
	
	public void excluirPorId(Long id) {
		this.escolaRepository.deleteById(id);
	}
	
}
