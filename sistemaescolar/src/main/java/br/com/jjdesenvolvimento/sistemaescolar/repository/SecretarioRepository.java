package br.com.jjdesenvolvimento.sistemaescolar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jjdesenvolvimento.sistemaescolar.model.Secretario;

public interface SecretarioRepository extends JpaRepository<Secretario, Long>{

	public Secretario findByLogin(String login);
	
	public Page<Secretario>  findByNomeContaining(String nome, Pageable pageable);
}
