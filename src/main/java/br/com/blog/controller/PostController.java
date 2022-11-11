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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blog.model.Posts;
import br.com.blog.repository.PostRepository;

@RestController
@RequestMapping("/post")
@CrossOrigin("* ")
public class PostController {
	@Autowired
	private PostRepository repo;

	@GetMapping
	// http://localhost:8080/post/
	public ResponseEntity<List<Posts>> GetAll() {
		return ResponseEntity.ok(repo.findAll());
	}

	@GetMapping("/{id}")
	// http://localhost:8080/post/1
	public ResponseEntity<Posts> GetById(@PathVariable Long id) {
		return repo.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/title/{title}")
	public ResponseEntity<List<Posts>> GetByTitle(@PathVariable String title) {
		return ResponseEntity.ok(repo.findAllByTitleContainingIgnoreCase(title));
	}

	@PostMapping
	public ResponseEntity<Posts> post(@RequestBody Posts post) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(post));
	}

	@PutMapping
	public ResponseEntity<Posts> put(@RequestBody Posts put) {
		return ResponseEntity.status(HttpStatus.OK).body(repo.save(put));
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repo.deleteById(id);
	}
}
