package br.com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blog.model.Theme;
import br.com.blog.repository.ThemeRepository;

@RestController
@RequestMapping("/theme")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ThemeController {
	@Autowired
	private ThemeRepository repository;

	@GetMapping
	public ResponseEntity<List<Theme>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Theme> getById(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/theme/{theme}")
	public ResponseEntity<List<Theme>> getByName(@PathVariable String theme) {
		return ResponseEntity.ok(repository.findAllByDescriptionContainingIgnoreCase(theme));
	}

	@PutMapping
	public ResponseEntity<Theme> put(@PathVariable Theme theme) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(theme));
	}

	@PostMapping
	public ResponseEntity<Theme> post(@PathVariable Theme theme) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(theme));
	}

	@DeleteMapping("/theme/{theme}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
