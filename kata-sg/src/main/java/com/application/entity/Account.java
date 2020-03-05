package com.application.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.application.exception.InsufficientBalanceException;

public class Account {
	
	private long id;
	private Amount balance;
	private List<Operation> operations;

	public Account() {
		balance = new Amount();
		balance.setCurrency(Currency.EUR);
		balance.setValue(BigDecimal.ZERO);
		operations = new ArrayList<Operation>();		
	}
	
	public void deposit(Amount depositAmount) {	
		balance.setValue(balance.getValue().add(depositAmount.getValue()));					
	}
	
	public void withdrawal(Amount withdrawalAmount) throws InsufficientBalanceException {
		if (withdrawalAmount.getValue().compareTo(balance.getValue()) > 0) {
			throw new InsufficientBalanceException();
		}
		
		balance.setValue(balance.getValue().subtract(withdrawalAmount.getValue()));					
	}
	
	public void addOperation(Operation operation) {
		operations.add(operation);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Amount getBalance() {
		return balance;
	}

	public void setBalance(Amount balance) {
		this.balance = balance;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
	public List<Operation> getOperations() {
		return operations;
	}

}
