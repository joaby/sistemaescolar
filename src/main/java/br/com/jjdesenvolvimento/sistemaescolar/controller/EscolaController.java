package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jjdesenvolvimento.sistemaescolar.model.Escola;
import br.com.jjdesenvolvimento.sistemaescolar.service.EscolaService;

@Controller
@RequestMapping("/escola")
public class EscolaController {
	
	@Autowired
	private EscolaService escolaService;
	
	@RequestMapping("/nova")
	public ModelAndView nova() {
		ModelAndView mv = new ModelAndView("escola/CadastroEscola");
		mv.addObject("escola", new Escola());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Escola escola, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return "escola/CadastroEscola";
		}
		escolaService.salvar(escola);
		attributes.addFlashAttribute("mensagem", "Escola salva com sucesso!");
		return "redirect:/escola/nova";
	}
	
	@RequestMapping("{id}")
	public ModelAndView editar(@PathVariable("id") Escola escola) {
		ModelAndView mv = new ModelAndView("escola/CadastroEscola");
		mv.addObject("escola", escola);
		return mv;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Long id) {
		this.escolaService.excluirPorId(id);
		return "redirect:/escola";
	}
	
	@RequestMapping
	public ModelAndView buscarTodas(){
		List<Escola> escolas = escolaService.buscarTodas();
		ModelAndView mv = new ModelAndView("escola/ListaEscolas");
		mv.addObject("escolas", escolas);
		return mv;
	}

}
