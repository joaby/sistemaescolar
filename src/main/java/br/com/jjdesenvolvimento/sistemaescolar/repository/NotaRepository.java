package br.com.jjdesenvolvimento.sistemaescolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jjdesenvolvimento.sistemaescolar.model.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long>{

}
