package br.com.jjdesenvolvimento.sistemaescolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jjdesenvolvimento.sistemaescolar.model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>{

}
