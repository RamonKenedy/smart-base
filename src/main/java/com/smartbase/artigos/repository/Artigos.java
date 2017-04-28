package com.smartbase.artigos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbase.artigos.model.Artigo;

public interface Artigos extends JpaRepository<Artigo, Long> {

	
	public List<Artigo> findByTituloContainingIgnoreCase(String titulo);
}
