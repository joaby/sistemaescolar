package br.com.jjdesenvolvimento.sistemaescolar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.Disciplina;
import br.com.jjdesenvolvimento.sistemaescolar.model.Escola;
import br.com.jjdesenvolvimento.sistemaescolar.model.Turma;
import br.com.jjdesenvolvimento.sistemaescolar.service.DisciplinaService;
import br.com.jjdesenvolvimento.sistemaescolar.service.EscolaService;
import br.com.jjdesenvolvimento.sistemaescolar.service.TurmaService;

@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {

	@Autowired
	private DisciplinaService disciplinaService;
	@Autowired
	private TurmaService turmaService;
	
	@RequestMapping("/nova/{id}")
	public ModelAndView nova(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("disciplina/CadastroDisciplina");
		mv.addObject("idTurma", id);
		return mv;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.POST)
	public String salvar(@PathVariable Long id, Disciplina disciplina) {
		disciplinaService.salvar(disciplina);
		Turma turma = turmaService.buscarPorId(id);
		//falta mais coisas
		return "escola/ListaEscolas";
	}
}
