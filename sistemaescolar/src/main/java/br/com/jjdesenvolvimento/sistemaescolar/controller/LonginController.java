package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.Aluno;
import br.com.jjdesenvolvimento.sistemaescolar.model.Secretario;
import br.com.jjdesenvolvimento.sistemaescolar.model.TipoUsuario;
import br.com.jjdesenvolvimento.sistemaescolar.security.UsuarioSistema;
import br.com.jjdesenvolvimento.sistemaescolar.service.AlunoService;
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
	
	@RequestMapping("/entrar")
	public String inicio() {
		return "Login";
	}
	
	@RequestMapping("/")
	public ModelAndView home(@AuthenticationPrincipal UsuarioSistema usuario){
		ModelAndView mv = new ModelAndView();
		if(usuario.getTipo().equals(TipoUsuario.SECRETARIO)) {
			Secretario secretario = this.secretarioService.buscarPorLogin(usuario.getUsername());
			mv.setViewName("secretario/HomeSecretario");
			mv.addObject("nome", secretario.getNome());
			mv.addObject("escola", secretario.getEscola());
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
