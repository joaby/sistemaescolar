package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.Professor;
import br.com.jjdesenvolvimento.sistemaescolar.service.ProfessorService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Professor professor) {
		professorService.salvar(professor);
		return "redirect:professor/novo";
	}
	
	@RequestMapping
	public ModelAndView buscarTodos() {
		List<Professor> professores = professorService.buscarTodos();
		ModelAndView mv = new ModelAndView();
		mv.addObject("professores", professores);
		return mv;
	}

}
