package br.com.jjdesenvolvimento.sistemaescolar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.Turma;
import br.com.jjdesenvolvimento.sistemaescolar.service.TurmaService;

@Controller
@RequestMapping("/turma")
public class TurmaController {

	private TurmaService turmaService;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	
	public String salvar(Turma turma) {
		turmaService.salvar(turma);
		return "";
	}
	
	
}
