package br.com.blog.service;

import java.nio.charset.Charset;
import java.util.Optional;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.blog.model.User;
import br.com.blog.model.UserLogin;
import br.com.blog.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;

//Method receive a user (all the attributes in it)
	public User Cadastrar(User user) {
		// Fire enconder for the password
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// Capture and encode password in a new variable
		String passwordEncoder = encoder.encode(user.getPassword());
		// Set password encoded into the class user
		user.setPassword(passwordEncoder);
		// save user into the database
		return repo.save(user);
	}

	public Optional<UserLogin> Logar(Optional<UserLogin> user) { 
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<User> usuario = repo.findByUser(user.get().getUser());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getPassword(), usuario.get().getPassword())) {
				String auth = user.get().getUser() + ":" + user.get().getPassword();

				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ACII")));
				String authHeader = "Basic " + new String(encodedAuth);
				user.get().setToken(authHeader);
				user.get().setUser(usuario.get().getUser());
				return user;
			}
		}
		return null;
	}
}
