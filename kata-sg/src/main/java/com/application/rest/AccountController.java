package com.application.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.entity.Operation;
import com.application.exception.InsufficientBalanceException;

@RestController
@RequestMapping("/account/{id}")
public interface AccountController {
	
	@GetMapping("/alloperations")
	public ResponseEntity<List<Operation>> getAccountOperations(@PathVariable("id") long accountId);
	
	@PutMapping(consumes = MediaType.ALL_VALUE, value="/deposit")
	public ResponseEntity<Void> deposit(@PathVariable("id") long accountId, @RequestBody Operation operation) throws InsufficientBalanceException;
	
	@PutMapping(consumes = MediaType.ALL_VALUE, value="/withdrawal")
	public ResponseEntity<Void> withdrawall(@PathVariable("id") long accountId, @RequestBody Operation operation) throws InsufficientBalanceException;

}
