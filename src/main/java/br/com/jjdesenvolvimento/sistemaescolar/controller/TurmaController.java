package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.Aluno;
import br.com.jjdesenvolvimento.sistemaescolar.model.Disciplina;
import br.com.jjdesenvolvimento.sistemaescolar.model.Escola;
import br.com.jjdesenvolvimento.sistemaescolar.model.Turma;
import br.com.jjdesenvolvimento.sistemaescolar.model.TurnoTurma;
import br.com.jjdesenvolvimento.sistemaescolar.service.AlunoService;
import br.com.jjdesenvolvimento.sistemaescolar.service.DisciplinaService;
import br.com.jjdesenvolvimento.sistemaescolar.service.EscolaService;
import br.com.jjdesenvolvimento.sistemaescolar.service.TurmaService;

@Controller
@RequestMapping("/turma")
public class TurmaController {

	@Autowired
	private TurmaService turmaService;
	@Autowired
	private EscolaService escolaService;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private DisciplinaService disciplinaService;
	
	@RequestMapping("/nova/{idEscola}")
	public ModelAndView novo(@PathVariable Long idEscola) {
		ModelAndView mv = new ModelAndView("turma/CadastroTurma");
		mv.addObject("idEscola", idEscola);
		return mv;
	}
	
	@RequestMapping(value="{idEscola}", method=RequestMethod.POST)
	public String salvar(@PathVariable Long idEscola, Turma turma) {
		Escola escola = escolaService.buscarPorId(idEscola);
		turma.setEscola(escola);
		turmaService.salvar(turma);
		return "redirect:/";
	}
	
	@RequestMapping("/{idEscola}/ano/turno")
	public ModelAndView buscarTurmasPorAnoTurno(@PathVariable Long idEscola, int ano, TurnoTurma turno) {
		Escola escola = escolaService.buscarPorId(idEscola);
		ModelAndView mv = new ModelAndView("secretario/HomeSecretario");
		List<Turma> turmasFiltradas = turmaService.filtrarTurmaPorAnoTurno(escola.getTurmas(), ano, turno);
		mv.addObject("escola", escola);
		mv.addObject("turmas", turmasFiltradas);
		return mv;
	}
	
	@RequestMapping("{idTurma}")
	public ModelAndView gereciarTurma(@PathVariable Long idTurma) {
		ModelAndView mv = new ModelAndView("turma/GerenciaTurma");
		Turma turma = turmaService.buscarPorId(idTurma);
		mv.addObject("turma", turma);
		return mv;
	}
	
	@RequestMapping(value="{idTurma}/aluno", method=RequestMethod.POST)
	public ModelAndView salvarAlunoTurma(@PathVariable Long idTurma, String matriculaAluno) {
		ModelAndView mv = new ModelAndView("turma/GerenciaTurma");
		Turma turma = turmaService.buscarPorId(idTurma);
		Aluno a = new Aluno();
		try {
			a = alunoService.buscarPorMatricula(matriculaAluno);
		} catch (Exception e) {
			System.out.println("Aluno não existe");
			mv.addObject("turma", turma);
			return mv;
		}
		turma.getAlunos().add(a);
		a.getTurmas().add(turma);
		turmaService.salvar(turma);
		alunoService.salvar(a);
		mv.addObject("turma", turma);
		return mv;
	}
	
	@RequestMapping(value="{idTurma}/aluno/{idAluno}", method=RequestMethod.DELETE)
	public ModelAndView excluirAlunoTurma(@PathVariable Long idTurma, @PathVariable Long idAluno) {
		ModelAndView mv = new ModelAndView("turma/GerenciaTurma");
		Turma turma = turmaService.buscarPorId(idTurma);
		Aluno aluno = alunoService.buscarPorId(idAluno);
		turma.getAlunos().remove(aluno);
		aluno.getTurmas().remove(turma);
		turmaService.salvar(turma);
		alunoService.salvar(aluno);
		mv.addObject("turma", turma);
		return mv;
	}
	
	@RequestMapping(value="{idTurma}/disciplina", method=RequestMethod.POST)
	public ModelAndView salvarDisciplinaTurma(@PathVariable Long idTurma, Disciplina disciplina) {
		ModelAndView mv = new ModelAndView("turma/GerenciaTurma");
		Turma turma = turmaService.buscarPorId(idTurma);
		disciplina.setTurma(turma);
		disciplinaService.salvar(disciplina);
		mv.addObject("turma", turma);
		return mv;
	}
	
	@ModelAttribute("turma")
	public Turma turmaVazia() {
		return new Turma();
	}
	
	@ModelAttribute("aluno")
	public Aluno alunoVAzio() {
		return new Aluno();
	}
	
	@ModelAttribute("disciplina")
	public Disciplina disciplinaVazia() {
		return new Disciplina();
	}
	
	@ModelAttribute("todosTurnos")
	public List<TurnoTurma> todosTurnos() {
		return Arrays.asList(TurnoTurma.values());
	}
}
