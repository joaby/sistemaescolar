package br.com.jjdesenvolvimento.sistemaescolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jjdesenvolvimento.sistemaescolar.model.Escola;

public interface EscolaRepository extends JpaRepository<Escola, Long>{

}
