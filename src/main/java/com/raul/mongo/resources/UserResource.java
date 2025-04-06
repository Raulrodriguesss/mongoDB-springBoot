package com.raul.mongo.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.raul.mongo.domain.User;

@RestController
@RequestMapping(value="/Users")
public class UserResource {

	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		User maria = new User("maria xiquinha", "maria@gmail.com", 1);
		User pedro = new User("pedro ferreira", "pedro@gmail.com", 2);
		List<User> list = new ArrayList<>();
		list.add(pedro);
		list.add(maria);
		return ResponseEntity.ok().body(list);
	}
}
