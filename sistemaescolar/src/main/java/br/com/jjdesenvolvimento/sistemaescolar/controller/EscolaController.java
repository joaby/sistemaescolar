package br.com.jjdesenvolvimento.sistemaescolar.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.jjdesenvolvimento.sistemaescolar.model.Escola;
import br.com.jjdesenvolvimento.sistemaescolar.model.Turma;
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
	
	@RequestMapping("{id}")
	public ModelAndView aditar(@PathVariable("id") Escola escola) {
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
	
	@RequestMapping("/{idEscola}/turmas")
	public ModelAndView buscarTodasTurmas(@PathVariable Long idEscola) {
		Escola escola = escolaService.buscarPorId(idEscola);
		ModelAndView mv = new ModelAndView("turma/ListaTurmas");
		Calendar hoje = Calendar.getInstance();
		int anoAtual = hoje.get(Calendar.YEAR);
		List<Turma> turmas = escola.getTurmas();
		List<Turma> turmasAnoAtual = new ArrayList<Turma>();
		for (int i = 0; i < turmas.size(); i++) {
			if (turmas.get(i).getAno() == anoAtual) {
				turmasAnoAtual.add(turmas.get(i));
			}
		}
		mv.addObject("idEscola", idEscola);
		mv.addObject("turmas", turmasAnoAtual);
		mv.addObject("infoEscola", escola.toString());
		return mv;
	}
	
	@RequestMapping("/{idEscola}/turmas/ano")
	public ModelAndView buscarTurmasPorAno(@PathVariable Long idEscola, int ano) {
		Escola escola = escolaService.buscarPorId(idEscola);
		ModelAndView mv = new ModelAndView("turma/ListaTurmas");
		List<Turma> turmas = escola.getTurmas();
		List<Turma> turmasAno = new ArrayList<Turma>();
		for (int i = 0; i < turmas.size(); i++) {
			if (turmas.get(i).getAno() == ano) {
				turmasAno.add(turmas.get(i));
			}
		}
		mv.addObject("idEscola", idEscola);
		mv.addObject("turmas", turmasAno);
		mv.addObject("infoEscola", escola.toString());
		return mv;
	}
	
	
	@ModelAttribute("escola")
	public Escola escolaVazia() {
		return new Escola();
	}

}
