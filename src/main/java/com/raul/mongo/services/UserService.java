package com.raul.mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.raul.mongo.domain.User;
import com.raul.mongo.dto.UserDTO;
import com.raul.mongo.repository.UserRepository;
import com.raul.mongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
	    return repo.findById(id)
	        .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User obj) {
 		return repo.insert(obj);
 	}
 	
 	public User fromDTO(UserDTO objDto) {
 		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
 	}
 	
 	public void delete(String id) {
 		findById(id);
 		repo.deleteById(id);
 	}
 	
 	public User update(User obj) {
 		Optional<User> newObj = repo.findById(obj.getId());
 		User user = newObj.orElse(obj);
 		updateData(user, obj);
 		return repo.save(user);
 	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}
}
