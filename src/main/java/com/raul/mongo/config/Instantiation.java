package com.raul.mongo.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.raul.mongo.domain.Post;
import com.raul.mongo.domain.User;
import com.raul.mongo.repository.PostRepository;
import com.raul.mongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		Post post1 = new Post(null, "Partiu viagem", "Vou viajar para São Paulo. Abraços!", maria, LocalDate.parse("21/03/2018", fmt));
		Post post2 = new Post(null, "Bom dia", "Acordei feliz hoje!", maria, LocalDate.parse("23/03/2018", fmt));
 		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		postRepository.saveAll(Arrays.asList(post1, post2));
		
	}

}
