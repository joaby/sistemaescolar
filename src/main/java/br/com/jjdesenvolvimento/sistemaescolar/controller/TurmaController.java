package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	//novo formulário
	@RequestMapping("/nova")
	public ModelAndView novo(@RequestParam Long idEscola) {
		ModelAndView mv = new ModelAndView("turma/CadastroTurma");
		mv.addObject("idEscola", idEscola);
		return mv;
	}
	
	//salva turma
	@RequestMapping(method=RequestMethod.POST)
	public String salvar(@RequestParam Long idEscola, @Validated Turma turma, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return "turma/CadastroTurma";
		}
		Optional<Escola> escola = escolaService.buscarPorId(idEscola);
		turma.setEscola(escola.get());
		turmaService.salvar(turma);
		attributes.addFlashAttribute("mensagem", "Turma salva com sucesso!");
		return "redirect:/turma/nova?idEscola="+idEscola;
	}
	
	//busca por ano e turno
	@RequestMapping("/ano/turno/{idEscola}")
	public ModelAndView buscarTurmasPorAnoTurno(@PathVariable Long idEscola, int ano, TurnoTurma turno) {
		Optional<Escola> escola = escolaService.buscarPorId(idEscola);
		ModelAndView mv = new ModelAndView("secretario/HomeSecretario");
		List<Turma> turmas = turmaService.buscarPorEscolaAnoTurno(escola.get(), ano, turno);
		mv.addObject("escola", escola.get());
		mv.addObject("turmas", turmas);
		return mv;
	}
	
	//gerencia turma
	@RequestMapping("{idTurma}")
	public ModelAndView gereciarTurma(@PathVariable Long idTurma) {
		ModelAndView mv = new ModelAndView("turma/GerenciaTurma");
		Turma turma = turmaService.buscarPorId(idTurma);
		mv.addObject("turma", turma);
		return mv;
	}
	
	//salva aluno em uma turma
	@RequestMapping(value="{idTurma}/aluno", method=RequestMethod.POST)
	public ModelAndView salvarAlunoTurma(@PathVariable Long idTurma, String matriculaAluno) {
		ModelAndView mv = new ModelAndView("turma/GerenciaTurma");
		Turma turma = turmaService.buscarPorId(idTurma);
		Optional<Aluno>	optionalAluno = alunoService.buscarPorMatricula(matriculaAluno);
		if(optionalAluno.isPresent()) {
			turma.getAlunos().add(optionalAluno.get());
			optionalAluno.get().getTurmas().add(turma);
			turmaService.salvar(turma);
			alunoService.salvar(optionalAluno.get());
			mv.addObject("turma", turma);
			return mv;
		}
		    mv.addObject("mensagemAdvertencia", "Aluno inexistente!");
			mv.addObject("turma", turma);
			return mv;
		
		
	}
	
	//exclui aluno de uma turma
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
		mv.addObject("mensagem", "Aluno foi excluido da turma com sucesso!");
		return mv;
	}
	
	//salva disciplina em uma turma
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
