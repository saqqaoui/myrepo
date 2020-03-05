package com.application.entity;

import java.util.Date;

public class Operation {
	
	private long creditorId;
	private long debtorId;
	private Date creationDate;
	private Amount Amount;
	private OperationType type;
	
	public long getDebtorId() {
		return debtorId;
	}

	public void setDebtorId(long debtorId) {
		this.debtorId = debtorId;
	}

	public long getCreditorId() {
		return creditorId;
	}

	public void setCreditorId(long creditorId) {
		this.creditorId = creditorId;
	}

	public Amount getAmount() {
		return Amount;
	}

	public void setAmount(Amount Amount) {
		this.Amount = Amount;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public OperationType getType() {
		return type;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public void setType(OperationType type) {
		this.type = type;
	}
}
