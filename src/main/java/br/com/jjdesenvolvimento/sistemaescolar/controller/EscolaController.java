package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jjdesenvolvimento.sistemaescolar.model.Escola;
import br.com.jjdesenvolvimento.sistemaescolar.service.EscolaService;

@Controller
@RequestMapping("/escola")
public class EscolaController {
	
	@Autowired
	private EscolaService escolaService;
	
	//novo formulário
	@RequestMapping("/nova")
	public ModelAndView nova() {
		ModelAndView mv = new ModelAndView("escola/CadastroEscola");
		mv.addObject("escola", new Escola());
		return mv;
	}
	
	//salva escola
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Escola escola, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return "escola/CadastroEscola";
		}
		escolaService.salvar(escola);
		attributes.addFlashAttribute("mensagem", "Escola salva com sucesso!");
		return "redirect:/escola/nova";
	}
	
	//pesquisa escola para editar
	@RequestMapping("{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("escola/CadastroEscola");
		Optional<Escola> escola = escolaService.buscarPorId(id);
		mv.addObject("escola", escola);
		return mv;
	}
	
	//deleta escola
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		this.escolaService.excluirPorId(id);
		attributes.addFlashAttribute("mensagem", "Escola excluida com sucesso!");
		return "redirect:/escola";
	}
	
	//busca todas escolas
	@RequestMapping
	public ModelAndView buscarTodas(@RequestParam(defaultValue="0") int page){
		Page<Escola> escolasPage = escolaService.buscarTodas(page, 10);
		ModelAndView mv = new ModelAndView("escola/ListaEscolas");
		mv.addObject("page", escolasPage);
		mv.addObject("currentPage", page);
		return mv;
	}

}
