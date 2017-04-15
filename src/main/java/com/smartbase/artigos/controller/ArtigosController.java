package com.smartbase.artigos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArtigosController {
	
	@GetMapping("/artigos/novo")
	public String novo(){
		return "artigo/cadastro-artigo";
	}
	
	@PostMapping
	public String salvar(){
		return null;
		//salvar artigo no banco de dados
	}

}
