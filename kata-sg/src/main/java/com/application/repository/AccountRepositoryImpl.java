package com.application.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.application.entity.Account;
import com.application.entity.Operation;
import com.application.entity.OperationType;
import com.application.exception.InsufficientBalanceException;


@Repository
public class AccountRepositoryImpl implements AccountRepository {
	
	private List<Account> accounts = new ArrayList<Account>();
	
	public AccountRepositoryImpl() {
		Account a = new Account();
		a.setId(1);
		
		Account b = new Account();
		b.setId(2);
		
		Account c = new Account();
		c.setId(3);
		
		accounts.addAll(Arrays.asList(a,b,c));
	}
	
	@Override
	public List<Operation> getAllOperations(long accountId) {
		return getAccountById(accountId, this.accounts).getOperations();
	}

	@Override
	public void executeOperation(long accountId, Operation operation) throws InsufficientBalanceException {

		Account account = getAccountById(accountId, this.accounts);
		if(OperationType.DEPOSIT == operation.getType()) {
			account.deposit(operation.getAmount());
		} else {
			try {
				account.withdrawal(operation.getAmount());
			} catch (InsufficientBalanceException e) {
				throw e;
			}	
		}
		
		operation.setCreationDate(new Date());
		account.addOperation(operation);
	}

	@Override
	public Account getAccountById(long accountId, List<Account> accounts) {
		return accounts.stream().filter(a -> a.getId() == accountId).findFirst().get();	
	}

}
