package com.application.repository;

import java.util.List;

import com.application.entity.Account;
import com.application.entity.Operation;
import com.application.exception.InsufficientBalanceException;

public interface AccountRepository {
	public Account getAccountById(long accountId, List<Account> accounts);
	public void executeOperation(long accountId, Operation operation) throws InsufficientBalanceException;
	public List<Operation> getAllOperations(long accountId);

}
