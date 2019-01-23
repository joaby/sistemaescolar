package br.com.jjdesenvolvimento.sistemaescolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jjdesenvolvimento.sistemaescolar.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{

	public Administrador findByLogin(String login);
}
