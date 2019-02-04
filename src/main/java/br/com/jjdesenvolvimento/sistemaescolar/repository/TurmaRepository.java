package br.com.jjdesenvolvimento.sistemaescolar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jjdesenvolvimento.sistemaescolar.model.Escola;
import br.com.jjdesenvolvimento.sistemaescolar.model.Turma;
import br.com.jjdesenvolvimento.sistemaescolar.model.TurnoTurma;

public interface TurmaRepository extends JpaRepository<Turma, Long>{
	
	public List<Turma> findByEscolaAndAnoOrderByTurno(Escola escola, int ano);
	public List<Turma> findByEscolaAndAnoAndTurno(Escola escola, int ano, TurnoTurma turno);

}
