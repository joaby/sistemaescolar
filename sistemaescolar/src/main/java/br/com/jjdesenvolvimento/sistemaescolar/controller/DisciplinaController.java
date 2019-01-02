package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.Aula;
import br.com.jjdesenvolvimento.sistemaescolar.model.Disciplina;
import br.com.jjdesenvolvimento.sistemaescolar.model.Escola;
import br.com.jjdesenvolvimento.sistemaescolar.model.Professor;
import br.com.jjdesenvolvimento.sistemaescolar.model.Turma;
import br.com.jjdesenvolvimento.sistemaescolar.security.UsuarioSistema;
import br.com.jjdesenvolvimento.sistemaescolar.service.AulaService;
import br.com.jjdesenvolvimento.sistemaescolar.service.DisciplinaService;
import br.com.jjdesenvolvimento.sistemaescolar.service.EscolaService;
import br.com.jjdesenvolvimento.sistemaescolar.service.ProfessorService;
import br.com.jjdesenvolvimento.sistemaescolar.service.TurmaService;

@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {

	@Autowired
	private DisciplinaService disciplinaService;
	@Autowired
	private TurmaService turmaService;
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private EscolaService escolaService;
	@Autowired
	private AulaService aulaService;
	
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
	
	@RequestMapping("/{idDisciplina}/professor/novo")
	public ModelAndView novoProfessorDisciplina(@PathVariable Long idDisciplina) {
		ModelAndView mv = new ModelAndView("disciplina/AdicionaProfessorDisciplina");
		mv.addObject("idDisciplina", idDisciplina);
		return mv;
	}
	
	@RequestMapping("/{idDisciplina}/aula/nova")
	public ModelAndView novaAulaDisciplina(@PathVariable Long idDisciplina) {
		ModelAndView mv = new ModelAndView("disciplina/CadastroAulaDisciplina");
		mv.addObject("idDisciplina", idDisciplina);
		return mv;
	}
	
	@RequestMapping(value="{idDisciplina}/aula", method=RequestMethod.POST)
	public ModelAndView salvarAulaDisciplina(@PathVariable Long idDisciplina, Aula aula) {
		ModelAndView mv = new ModelAndView("disciplina/CadastroAulaDisciplina");
		Disciplina disciplina = disciplinaService.buscarPorId(idDisciplina);
		aula.setDisciplina(disciplina);
		aulaService.salvar(aula);
		mv.addObject("idDisciplina", idDisciplina);
		return mv;
	}
	
	@RequestMapping(value="/{idDisciplina}/professor", method=RequestMethod.POST)
	public ModelAndView adicionarProfessorDisciplina(@PathVariable Long idDisciplina, String cpf) {
		ModelAndView mv = new ModelAndView("disciplina/AdicionaProfessorDisciplina");
		Disciplina disciplina = disciplinaService.buscarPorId(idDisciplina);
		Professor professor = professorService.buscarPorCpf(cpf);
		disciplina.getProfessores().add(professor);
		disciplinaService.salvar(disciplina);
		mv.addObject("idDisciplina", idDisciplina);
		return mv;
	}
	
	@RequestMapping("/professor")
	public ModelAndView buscarDisciplinasProfessor(@AuthenticationPrincipal UsuarioSistema usuario, Long idEscola, int anoLetivo) {
		ModelAndView mv = new ModelAndView("professor/HomeProfessor");
		Professor professor = professorService.buscarPorLogin(usuario.getUsername());
		Escola escola = escolaService.buscarPorId(idEscola);
		List<Turma> turmas = escola.getTurmas();
		List<Disciplina> disciplinasProfessor = new ArrayList<Disciplina>();
		for (Turma turma : turmas) {
			if(turma.getAno() == anoLetivo) {
				for (Disciplina disciplina : turma.getDisciplinas()) {
					for (Professor p : disciplina.getProfessores()) {
						if(professor.getCpf().equals(p.getCpf())) {
							disciplinasProfessor.add(disciplina);
						}
					}
				}
			}
		}
		mv.addObject("nome", usuario.getNome());
		mv.addObject("todasEscolas", escolaService.buscarTodas());
		mv.addObject("disciplinas", disciplinasProfessor);
		return mv;
	}
	
	@RequestMapping(value="/{idDisciplina}/professor/{cpf}", method=RequestMethod.DELETE)
	public @ResponseBody String excluirProfessorDisciplina(@PathVariable Long idDisciplina, @PathVariable String cpf) {
		Disciplina disciplina = disciplinaService.buscarPorId(idDisciplina);
		Professor professor = professorService.buscarPorCpf(cpf);
		List<Professor> professores = disciplina.getProfessores();
		for (int i = 0; i < professores.size(); i++) {
			if (professores.get(i).getCpf() == professor.getCpf()) {
				disciplina.getProfessores().remove(i);
				break;
			}
		}
		disciplinaService.salvar(disciplina);
		return "ok";
	}
	
	@ModelAttribute("disciplina")
	public Disciplina disciplinaVazia() {
		return new Disciplina();
	}
	@ModelAttribute("aula")
	public Aula aulaVazia() {
		return new Aula();
	}
	
}
