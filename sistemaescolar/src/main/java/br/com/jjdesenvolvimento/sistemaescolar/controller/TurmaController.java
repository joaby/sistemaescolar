package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.Escola;
import br.com.jjdesenvolvimento.sistemaescolar.model.Turma;
import br.com.jjdesenvolvimento.sistemaescolar.model.TurnoTurma;
import br.com.jjdesenvolvimento.sistemaescolar.service.EscolaService;
import br.com.jjdesenvolvimento.sistemaescolar.service.TurmaService;

@Controller
@RequestMapping("/turma")
public class TurmaController {

	@Autowired
	private TurmaService turmaService;
	@Autowired
	private EscolaService escolaService;
	
	@RequestMapping("/nova/{idEscola}")
	public ModelAndView novo(@PathVariable Long idEscola) {
		ModelAndView mv = new ModelAndView("turma/CadastroTurma");
		Escola escola = escolaService.buscarPorId(idEscola);
		mv.addObject("idEscola", idEscola);
		mv.addObject("infoEscola", escola.toString());
		mv.addObject("turmas", escola.getTurmas());
		return mv;
	}
	
	@RequestMapping(value="{idEscola}", method=RequestMethod.POST)
	public ModelAndView salvar(@PathVariable Long idEscola, Turma turma) {
		Escola escola = escolaService.buscarPorId(idEscola);
		turma.setEscola(escola);
		turmaService.salvar(turma);
		ModelAndView mv = new ModelAndView("turma/CadastroTurma");
		mv.addObject("idEscola", idEscola);
		mv.addObject("turmas", escola.getTurmas());
		mv.addObject("infoEscola", escola.toString());
		return mv;
	}
	
	@RequestMapping("{idTurma}")
	public ModelAndView gereciarTurma(@PathVariable Long idTurma) {
		ModelAndView mv = new ModelAndView("turma/GerenciaTurma");
		Turma turma = turmaService.buscarPorId(idTurma);
		mv.addObject("infoEscola", turma.getEscola().toString());
		mv.addObject("infoTurma", turma.getNome());
		mv.addObject("alunos", turma.getAlunos());
		mv.addObject("disciplinas", turma.getDisciplinas());
		return mv;
	}
	
	@ModelAttribute("turma")
	public Turma turmaVazia() {
		return new Turma();
	}
	
	@ModelAttribute("todosTurnos")
	public List<TurnoTurma> todosTurnos() {
		return Arrays.asList(TurnoTurma.values());
	}
}
