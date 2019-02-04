package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("secretario/CadastroSecretario");
		mv.addObject("secretario", new Secretario());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Secretario secretario, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return "secretario/CadastroSecretario";
		}
		secretario.setLogin(secretario.getCpf());
		secretario.setSenha(secretario.getCpf());
		this.secretarioService.salvar(secretario);
		attributes.addFlashAttribute("mensagem", "Secretário salvo com sucesso!" );
		return "redirect:/secretario/novo";
	}
	
	@RequestMapping("{id}")
	public ModelAndView editar(@PathVariable("id") Secretario secretario) {
		ModelAndView mv = new ModelAndView("secretario/CadastroSecretario");
		mv.addObject("secretario", secretario);
		return mv;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		secretarioService.excluir(id);
		return "redirect:/secretario";
	}
	
	@RequestMapping
	public ModelAndView buscarTodos(@RequestParam(defaultValue="0") int page) {
		ModelAndView mv = new ModelAndView("secretario/ListaSecretarios");
		mv.addObject("page", this.secretarioService.buscarTodos(page, 20));
		mv.addObject("currentPage", page);
		return mv;
	}
	
	@RequestMapping("/nome")
	public ModelAndView buscarPorNome(@RequestParam(defaultValue="0") int page, @RequestParam String nome) {
		ModelAndView mv = new ModelAndView("secretario/ListaSecretarios");
		mv.addObject("page", this.secretarioService.buscarPorNome(nome, page, 10));
		mv.addObject("currentPage", page);
		return mv;
	}
	
	@ModelAttribute("todasEscolas")
	public List<Escola> buscarEscolas(){
		return this.escolaService.buscarTodas();
	}
}
