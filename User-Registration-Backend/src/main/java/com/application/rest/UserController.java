package com.application.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.dao.UserRepository;
import com.application.exception.ApiError;
import com.application.model.User;

@RestController
@CrossOrigin(origins = "*")
@Validated
public class UserController {

	@Autowired
	private UserRepository repository;

	@GetMapping("/getAllUsers")
	public ResponseEntity<Object> findAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findUserById(@PathVariable int id) {
		final Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(user.get());
		}

		return userNotFoundResponse();
	}

	@PostMapping("/register")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(user));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody User user) {
		final Optional<User> OptionalOfUserToUpdate = repository.findById(id);
		if (OptionalOfUserToUpdate.isPresent()) {
			final User userToUpdate = OptionalOfUserToUpdate.get();
			userToUpdate.setName(user.getName());
			userToUpdate.setEmail(user.getEmail());
			return ResponseEntity.status(HttpStatus.OK).body(repository.save(userToUpdate));
		}

		return userNotFoundResponse();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		final Optional<User> userToDelete = repository.findById(id);
		if(userToDelete.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return userNotFoundResponse();
	}

	private ResponseEntity<Object> userNotFoundResponse() {
		final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "No user with this ID is registered","");
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}
