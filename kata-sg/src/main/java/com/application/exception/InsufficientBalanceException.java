package com.application.exception;

public class InsufficientBalanceException extends Exception {

	private static final long serialVersionUID = 4772170844440090293L;
	
	private static final String INSUFFICIENTBALANCEEXCEPTIONMESSAGE = "Transaction denied, insufficient balance";

	public InsufficientBalanceException() {
		super(INSUFFICIENTBALANCEEXCEPTIONMESSAGE);	
	}

}
