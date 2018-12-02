package br.com.jjdesenvolvimento.sistemaescolar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.Disciplina;
import br.com.jjdesenvolvimento.sistemaescolar.model.Turma;
import br.com.jjdesenvolvimento.sistemaescolar.service.DisciplinaService;
import br.com.jjdesenvolvimento.sistemaescolar.service.TurmaService;

@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {

	@Autowired
	private DisciplinaService disciplinaService;
	@Autowired
	private TurmaService turmaService;
	
	@RequestMapping("/nova/{idTurma}")
	public ModelAndView nova(@PathVariable Long idTurma) {
		ModelAndView mv = new ModelAndView("disciplina/CadastroDisciplina");
		mv.addObject("idTurma", idTurma);
		return mv;
	}
	
	@RequestMapping(value="{idTurma}", method=RequestMethod.POST)
	public ModelAndView salvar(@PathVariable Long idTurma, Disciplina disciplina) {
		Turma turma = turmaService.buscarPorId(idTurma);
		disciplina.setTurma(turma);
		disciplinaService.salvar(disciplina);
		ModelAndView mv = new ModelAndView("disciplina/ListaDisciplinas");
		mv.addObject("disciplinas", turma.getDisciplinas());
		return mv;
	}
	
	@RequestMapping("{idTurma}")
	public ModelAndView buscarDisciplinasPorTurma(@PathVariable Long idTurma) {
		ModelAndView mv = new ModelAndView("disciplina/ListaDisciplinas");
		mv.addObject("disciplinas", turmaService.buscarPorId(idTurma).getDisciplinas());
		return mv;
	}
	
	@ModelAttribute("disciplina")
	public Disciplina disciplinaVazia() {
		return new Disciplina();
	}
}
