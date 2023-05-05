package com.gh.ghmoney.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gh.ghmoney.api.model.Categoria;
import com.gh.ghmoney.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;

	
	/**
	 * @author Glecio Heybel
	 * @return lista de categorias
	 */
	@GetMapping
	public List<Categoria> listarCategorias() {
		return categoriaRepository.findAll();

	}

	/**
	 * @author Glecio Heybel
	 * @param categoria
	 * @param response
	 * @return categoria
	 */
	@PostMapping
	public ResponseEntity<Categoria> criarCategoria( @Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(categoriaSalva.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(categoriaSalva);

	}

	/**
	 * @author Glecio Heybel
	 * @param codigo
	 * @return categoria
	 */
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarCategoriaPorCodigo(@PathVariable Long codigo) {
		Optional<Categoria> categoria = categoriaRepository.findById(codigo);
		return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();

	}
}
