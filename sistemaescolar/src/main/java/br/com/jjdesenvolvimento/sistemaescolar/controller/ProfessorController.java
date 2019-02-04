package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.FormacaoProfessor;
import br.com.jjdesenvolvimento.sistemaescolar.model.Professor;
import br.com.jjdesenvolvimento.sistemaescolar.service.ProfessorService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("professor/CadastroProfessor");
		return mv;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Professor professor) {
		professor.setLogin(professor.getCpf());
		professor.setSenha(professor.getCpf());
		professorService.salvar(professor);
		return "redirect:professor/novo";
	}
	
	@RequestMapping
	public ModelAndView buscarTodos() {
		List<Professor> professores = professorService.buscarTodos();
		ModelAndView mv = new ModelAndView("professor/ListaProfessores");
		mv.addObject("professores", professores);
		return mv;
	}
	
	@ModelAttribute("todasFormacao")
	public List<FormacaoProfessor> todasFormacao(){
		return Arrays.asList(FormacaoProfessor.values());
	}
	
	@ModelAttribute("professor")
	public Professor professorVazio() {
		return new Professor();
	}
}
