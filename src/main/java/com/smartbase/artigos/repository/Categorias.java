package com.smartbase.artigos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbase.artigos.model.Categoria;

public interface Categorias extends JpaRepository<Categoria, Long> {

	public List<Categoria> findByDescricaoContainingIgnoreCase(String descricao);

	public Optional<Categoria> findByDescricaoIgnoreCase(String descricao);
}
