package com.smartbase.artigos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.smartbase.artigos.model.Artigo;
import com.smartbase.artigos.model.Categoria;
import com.smartbase.artigos.repository.Artigos;

@Controller
public class ArtigosController {

	@Autowired
	private Artigos artigos;

	@GetMapping("/artigos/novo")
	public String novo(Model model) {
		model.addAttribute(new Artigo());
		// model.addAttribute("categorias", Categoria.)
		return "artigo/cadastro-artigo";
	}

	@PostMapping("/artigos/novo")
	public String salvar(Artigo artigo) {
		artigos.save(artigo);
		return "redirect:/artigos/novo";
	}

}
