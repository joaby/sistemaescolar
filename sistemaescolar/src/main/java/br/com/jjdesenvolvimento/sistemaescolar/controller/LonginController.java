package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.jjdesenvolvimento.sistemaescolar.model.Aluno;
import br.com.jjdesenvolvimento.sistemaescolar.model.TipoUsuario;
import br.com.jjdesenvolvimento.sistemaescolar.service.AlunoService;
import br.com.jjdesenvolvimento.sistemaescolar.service.EscolaService;
import br.com.jjdesenvolvimento.sistemaescolar.service.ProfessorService;
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
	
	@RequestMapping("/inicio")
	public String inicio() {
		return "Login";
	}
	
	@RequestMapping(value="/logar", method=RequestMethod.POST)
	public String logar(Long usuario, String senha, TipoUsuario tipo) {
		if(tipo.equals(TipoUsuario.ALUNO)) {
			Aluno a = alunoService.buscarPorId(usuario);
			if(a!=null && senha.equals(a.getSenha())) {
				return "redirect:aluno/novo";
			}else {
				return "Login";
			}
			
		}
		return "Login";
	}
	
	@ModelAttribute("todosTipoUsuario")
	public List<TipoUsuario> todosTipoUsuario(){
		return Arrays.asList(TipoUsuario.values());
	}

}
