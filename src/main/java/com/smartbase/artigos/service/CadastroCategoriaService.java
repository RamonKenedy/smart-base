package com.smartbase.artigos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartbase.artigos.model.Categoria;
import com.smartbase.artigos.repository.Categorias;

@Service
public class CadastroCategoriaService {

	@Autowired
	private Categorias categorias;

	@Transactional
	public Categoria salvar(Categoria categoria) {
		Optional<Categoria> categoriaOptional = categorias.findByDescricaoIgnoreCase(categoria.getDescricao());
		if (categoriaOptional.isPresent()) {
			throw new RuntimeException("Categoria já cadastrada");
		}

		return categorias.saveAndFlush(categoria);
	}

}