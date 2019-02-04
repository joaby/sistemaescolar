package br.com.jjdesenvolvimento.sistemaescolar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.jjdesenvolvimento.sistemaescolar.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	public Optional<Aluno> findByMatricula(String matricula);
}
