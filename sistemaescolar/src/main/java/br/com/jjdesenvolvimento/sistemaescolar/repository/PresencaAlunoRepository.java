package br.com.jjdesenvolvimento.sistemaescolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jjdesenvolvimento.sistemaescolar.model.PresencaAluno;

public interface PresencaAlunoRepository extends JpaRepository<PresencaAluno, Long>{

}
