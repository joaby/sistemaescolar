package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.Administrador;
import br.com.jjdesenvolvimento.sistemaescolar.model.Aluno;
import br.com.jjdesenvolvimento.sistemaescolar.model.Professor;
import br.com.jjdesenvolvimento.sistemaescolar.model.Secretario;
import br.com.jjdesenvolvimento.sistemaescolar.model.TipoUsuario;
import br.com.jjdesenvolvimento.sistemaescolar.model.TurnoTurma;
import br.com.jjdesenvolvimento.sistemaescolar.security.UsuarioSistema;
import br.com.jjdesenvolvimento.sistemaescolar.service.AdministradorService;
import br.com.jjdesenvolvimento.sistemaescolar.service.AlunoService;
import br.com.jjdesenvolvimento.sistemaescolar.service.DisciplinaService;
import br.com.jjdesenvolvimento.sistemaescolar.service.EscolaService;
import br.com.jjdesenvolvimento.sistemaescolar.service.ProfessorService;
import br.com.jjdesenvolvimento.sistemaescolar.service.SecretarioService;
import br.com.jjdesenvolvimento.sistemaescolar.service.TurmaService;

@Controller
public class LonginController {

	@Autowired
	private EscolaService escolaService;
	@Autowired
	private TurmaService turmaService;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private SecretarioService secretarioService;
	@Autowired
	private AdministradorService administradorService;
	@Autowired
	private DisciplinaService disciplinaService;
	
	@RequestMapping("/entrar")
	public String inicio() {
		return "Login";
	}
	
	@RequestMapping("/")
	public ModelAndView home(@AuthenticationPrincipal UsuarioSistema usuario){
		ModelAndView mv = new ModelAndView();
		Calendar hoje = Calendar.getInstance();
		if(usuario.getTipo().equals(TipoUsuario.SECRETARIO)) {
			Secretario secretario = this.secretarioService.buscarPorLogin(usuario.getUsername());
			mv.setViewName("secretario/HomeSecretario");
			mv.addObject("escola", secretario.getEscola());
			mv.addObject("turmas", turmaService.buscarPorEscolaAno(secretario.getEscola(), hoje.get(Calendar.YEAR)));
			mv.addObject("todosTurnos", Arrays.asList(TurnoTurma.values()));
			return mv;
		}else if(usuario.getTipo().equals(TipoUsuario.PROFESSOR)) {
			Professor professor = this.professorService.buscarPorLogin(usuario.getUsername());
			mv.setViewName("professor/HomeProfessor");
			mv.addObject("nome", professor.getNome());
			mv.addObject("todasEscolas", escolaService.buscarTodas());
			mv.addObject("disciplinas", disciplinaService.buscarDisciplinasPorAno(professor.getDisciplinas(), hoje.get(Calendar.YEAR)));
			return mv;
		}else if(usuario.getTipo().equals(TipoUsuario.ADMINISTRADOR)) {
			Administrador administrador = this.administradorService.buscarPorLogin(usuario.getUsername());
			mv.setViewName("administrador/HomeAdministrador");
			mv.addObject("nome", administrador.getNome());
			return mv;
		}
		mv.setViewName("Home");
		return mv;
	}
	
	@ModelAttribute("todosTipoUsuario")
	public List<TipoUsuario> todosTipoUsuario(){
		return Arrays.asList(TipoUsuario.values());
	}

}
