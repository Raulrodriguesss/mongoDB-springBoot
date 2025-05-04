package com.raul.mongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.raul.mongo.domain.User;
import com.raul.mongo.dto.UserDTO;
import com.raul.mongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
	    User user = service.findById(id);
	    return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@PostMapping
  	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
 		User obj = service.fromDTO(objDto);
 		obj = service.insert(obj);
 		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
 		return ResponseEntity.created(uri).build();
 	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
	    service.delete(id);
	    return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
  	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
 		User obj = service.fromDTO(objDto);
 		obj.setId(id);
 		obj = service.update(obj);
 		return ResponseEntity.noContent().build();
 	}
}
