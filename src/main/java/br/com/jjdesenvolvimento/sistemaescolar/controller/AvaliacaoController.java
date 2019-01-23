package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.Aluno;
import br.com.jjdesenvolvimento.sistemaescolar.model.Avaliacao;
import br.com.jjdesenvolvimento.sistemaescolar.model.Disciplina;
import br.com.jjdesenvolvimento.sistemaescolar.model.Nota;
import br.com.jjdesenvolvimento.sistemaescolar.service.AvaliacaoService;
import br.com.jjdesenvolvimento.sistemaescolar.service.DisciplinaService;
import br.com.jjdesenvolvimento.sistemaescolar.service.NotaService;

@Controller
@RequestMapping("/avaliacao")
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	@Autowired
	private NotaService notaService;
	@Autowired
	private DisciplinaService disciplinaService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(@RequestParam("idDisciplina") Long idDisciplina) {
		ModelAndView mv = new ModelAndView("avaliacao/CadastroAvaliacao");
		mv.addObject("idDisciplina", idDisciplina);
		mv.addObject("avaliacao", new Avaliacao());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView salvar(@RequestParam("idDisciplina") Long idDisciplina, Avaliacao avaliacao) {
		ModelAndView mv = new ModelAndView("avaliacao/CadastroAvaliacao");
		Disciplina disciplina = disciplinaService.buscarPorId(idDisciplina);
		avaliacao.setDisciplina(disciplina);
		avaliacaoService.salvar(avaliacao);
		List<Aluno> alunos = disciplina.getTurma().getAlunos();
		for (Aluno aluno : alunos) {
			Nota nota = new Nota(0, aluno, avaliacao);
			notaService.salvar(nota);
		}
		mv.addObject("idDisciplina", idDisciplina);
		return mv;
	}
	
	@RequestMapping("/gerencia")
	public ModelAndView gerenciar(@RequestParam("idDisciplina") Long idDisciplina) {
		ModelAndView mv = new ModelAndView("avaliacao/GerenciaAvaliacao");
		Disciplina disciplina = disciplinaService.buscarPorId(idDisciplina);
		mv.addObject("idDisciplina", idDisciplina);
		mv.addObject("avaliacoes", disciplina.getAvaliacoes());
		return mv;
	}
	
	@RequestMapping("/notas/nova")
	public ModelAndView novaNotas(@RequestParam("idAvaliacao") Long idAvaliacao) {
		ModelAndView mv = new ModelAndView("avaliacao/CadastroNotas");
		Avaliacao avaliacao = avaliacaoService.buscarPorId(idAvaliacao);
		mv.addObject("avaliacao", avaliacao);
		return mv;
	}
	
	@RequestMapping(value="/notas", method=RequestMethod.POST)
	public ModelAndView salvarNotas(Avaliacao avaliacao) {
		ModelAndView mv = new ModelAndView("avaliacao/CadastroNotas");
		List<Nota> notas = avaliacao.getNotas();
		for (Nota nota : notas) {
			notaService.salvar(nota);
		}
		return mv;
	}

}
