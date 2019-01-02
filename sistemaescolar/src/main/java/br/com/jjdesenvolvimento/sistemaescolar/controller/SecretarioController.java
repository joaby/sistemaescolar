package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.Escola;
import br.com.jjdesenvolvimento.sistemaescolar.model.Secretario;
import br.com.jjdesenvolvimento.sistemaescolar.service.EscolaService;
import br.com.jjdesenvolvimento.sistemaescolar.service.SecretarioService;

@Controller
@RequestMapping("/secretario")
public class SecretarioController {

	@Autowired
	private SecretarioService secretarioService;
	@Autowired
	private EscolaService escolaService;
	
	@RequestMapping("/novo")
	public String novo() {
		return "secretario/CadastroSecretario";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Secretario secretario, Long idEscola) {
		Escola escola = this.escolaService.buscarPorId(idEscola);
		secretario.setEscola(escola);
		secretario.setLogin(secretario.getCpf());
		secretario.setSenha(secretario.getCpf());
		this.secretarioService.salvar(secretario);
		return "redirect:secretario/novo";
	}
	
	@RequestMapping
	public ModelAndView buscarTodos() {
		ModelAndView mv = new ModelAndView("secretario/ListaSecretarios");
		mv.addObject("secretarios", this.secretarioService.buscarTodos());
		return mv;
	}
	
	@ModelAttribute("secretario")
	public Secretario secretarioVazio() {
		return new Secretario();
	}
	
	@ModelAttribute("todasEscolas")
	public List<Escola> buscarEscolas(){
		return this.escolaService.buscarTodas();
	}
}
