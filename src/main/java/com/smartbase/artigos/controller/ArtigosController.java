package com.smartbase.artigos.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smartbase.artigos.model.Artigo;
import com.smartbase.artigos.repository.Artigos;
import com.smartbase.artigos.repository.Categorias;
import com.smartbase.artigos.repository.filter.ArtigoFilter;

@Controller
@RequestMapping("/artigos")
public class ArtigosController {

	@Autowired
	private Artigos artigos;
	@Autowired
	private Categorias categorias;

	@GetMapping("/novo")
	public ModelAndView novo(Artigo artigo) {
		ModelAndView mv = new ModelAndView("artigo/cadastro-artigo");
		mv.addObject(artigo);
		mv.addObject("categorias", categorias.findAll());
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Artigo artigo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(artigo);
		}
		artigos.save(artigo);
		attributes.addFlashAttribute("mensagem", "Artigo salvo com sucesso!");
		return new ModelAndView("redirect:/artigos/novo");
	}

	@GetMapping
	public ModelAndView pesquisar(ArtigoFilter artigoFilter) {
		ModelAndView mv = new ModelAndView("/artigo/pesquisa-artigos");
		mv.addObject("artigos",
				artigos.findByTituloContainingIgnoreCase(Optional.ofNullable(artigoFilter.getTitulo()).orElse("%")));
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Artigo artigo = artigos.findOne(id);
		return novo(artigo);

	}

	@DeleteMapping("/{id}")
	public String apagar(@PathVariable Long id, RedirectAttributes attributes) {
		artigos.delete(id);
		attributes.addFlashAttribute("mensagem", "Artigo removido com sucesso!");
		return "redirect:/artigos";
	}

}
