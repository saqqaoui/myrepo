package com.application.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.application.entity.Operation;
import com.application.entity.OperationType;
import com.application.exception.InsufficientBalanceException;
import com.application.repository.AccountRepository;

@Controller
public class AccountControllerImpl implements AccountController {

	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public ResponseEntity<List<Operation>> getAccountOperations(long accountId) {
		return new ResponseEntity<List<Operation>>(accountRepository.getAllOperations(accountId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> deposit(long accountId, Operation operation) throws InsufficientBalanceException {
		operation.setType(OperationType.DEPOSIT);
		try {
			return this.executeOperation(accountId, operation);
		} catch (InsufficientBalanceException e) {
			throw e;
		}
	}

	@Override
	public ResponseEntity<Void> withdrawall(long accountId, Operation operation) throws InsufficientBalanceException {
		operation.setType(OperationType.WITHDRAWALL);
		try {
			return this.executeOperation(accountId, operation);
		} catch (InsufficientBalanceException e) {
			throw e;
		}
	}
	
	private ResponseEntity<Void> executeOperation(long accountId, Operation operation) throws InsufficientBalanceException {
		accountRepository.executeOperation(accountId, operation);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
