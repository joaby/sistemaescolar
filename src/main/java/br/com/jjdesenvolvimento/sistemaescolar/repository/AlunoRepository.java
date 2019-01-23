package br.com.jjdesenvolvimento.sistemaescolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.jjdesenvolvimento.sistemaescolar.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	public Aluno findByMatricula(String matricula);
}
