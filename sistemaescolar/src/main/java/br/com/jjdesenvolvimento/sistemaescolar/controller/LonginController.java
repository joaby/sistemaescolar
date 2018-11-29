package br.com.jjdesenvolvimento.sistemaescolar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.jjdesenvolvimento.sistemaescolar.service.AlunoService;
import br.com.jjdesenvolvimento.sistemaescolar.service.EscolaService;
import br.com.jjdesenvolvimento.sistemaescolar.service.ProfessorService;
import br.com.jjdesenvolvimento.sistemaescolar.service.TurmaService;

@Controller
public class LonginController {

	@Autowired
	private EscolaService escolaService;
	@Autowired
	private TurmaService turmaService;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private ProfessorService professorService;

}
