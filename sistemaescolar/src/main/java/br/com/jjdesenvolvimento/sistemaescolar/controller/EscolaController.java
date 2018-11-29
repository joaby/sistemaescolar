package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.Escola;
import br.com.jjdesenvolvimento.sistemaescolar.service.EscolaService;

@Controller
@RequestMapping("escola")
public class EscolaController {
	
	@Autowired
	private EscolaService escolaService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Escola escola) {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	
	public String salvar(Escola escola) {
		escolaService.salvar(escola);
		return "";
	}
	
	public List<Escola> buscarTodas(){
		return escolaService.buscarTodas();
	}

}
