package br.com.jjdesenvolvimento.sistemaescolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.jjdesenvolvimento.sistemaescolar.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
	public Professor findByLogin(String login);
	public Professor findByCpf(String cpf);

}
