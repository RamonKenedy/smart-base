package com.smartbase.artigos.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SegurancaController {

	@RequestMapping("/login")
	// @AuthenticationPrincipal pega o usuario logado
	public String login(@AuthenticationPrincipal User user) {
		if (user != null) {
			return "redirect:/artigos";
		}

		return "login";
	}

}