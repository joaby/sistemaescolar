package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.Aluno;
import br.com.jjdesenvolvimento.sistemaescolar.service.AlunoService;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("aluno/CadastroAluno");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Aluno aluno) {
		alunoService.salvar(aluno);
		return "redirect:aluno/novo";
	}
	
	@RequestMapping
	public ModelAndView buscarTodos() {
		List<Aluno> alunos = alunoService.buscarTodos();
		ModelAndView mv = new ModelAndView("aluno/CadastroAluno");
		mv.addObject("alunos", alunos);
		return mv;
	}
}
