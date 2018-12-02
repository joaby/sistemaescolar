package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.Escola;
import br.com.jjdesenvolvimento.sistemaescolar.service.EscolaService;

@Controller
@RequestMapping("/escola")
public class EscolaController {
	
	@Autowired
	private EscolaService escolaService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Escola escola) {
		ModelAndView mv = new ModelAndView("escola/CadastroEscola");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Escola escola) {
		escolaService.salvar(escola);
		return "redirect:escola/nova";
	}
	
	@RequestMapping
	public ModelAndView buscarTodas(){
		List<Escola> escolas = escolaService.buscarTodas();
		ModelAndView mv = new ModelAndView("escola/ListaEscolas");
		mv.addObject("escolas", escolas);
		return mv;
	}
	
	@RequestMapping("/{idEscola}/turmas")
	public ModelAndView buscarTodasTurmas(@PathVariable Long idEscola) {
		Escola escola = escolaService.buscarPorId(idEscola);
		ModelAndView mv = new ModelAndView("turma/ListaTurmas");
		mv.addObject("turmas", escola.getTurmas());
		mv.addObject("infoEscola", escola.toString());
		return mv;
	}
	
	
	@ModelAttribute("escola")
	public Escola escolaVazia() {
		return new Escola();
	}

}
