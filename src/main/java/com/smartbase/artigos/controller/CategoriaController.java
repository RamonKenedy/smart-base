package com.smartbase.artigos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartbase.artigos.model.Categoria;
import com.smartbase.artigos.service.CadastroCategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CadastroCategoriaService cadastroCategoriaService;

	@Autowired

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Categoria categoria, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
		}

		categoria = cadastroCategoriaService.salvar(categoria);
		return ResponseEntity.ok(categoria);
	}

}