package br.com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blog.model.Posts;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {
	public List<Posts> findAllByTitleContainingIgnoreCase(String title);
}
