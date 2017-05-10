package com.smartbase.artigos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smartbase.artigos.model.Categoria;
import com.smartbase.artigos.service.CadastroCategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CadastroCategoriaService cadastroCategoriaService;

	@RequestMapping("/novo")
	public ModelAndView novo(Categoria categoria) {
		return new ModelAndView("categoria/CadastroCategoria");
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Categoria categoria, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(categoria);
		}

		try {
			cadastroCategoriaService.salvar(categoria);
		} catch (RuntimeException e) {
			result.rejectValue("descricao", e.getMessage(), e.getMessage());
			return novo(categoria);
		}

		attributes.addFlashAttribute("mensagem", "Categoria salvo com sucesso");
		return new ModelAndView("redirect:/categorias/novo");
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(Categoria categoria, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
		}

		categoria = cadastroCategoriaService.salvar(categoria);
		return ResponseEntity.ok(categoria);
	}

}