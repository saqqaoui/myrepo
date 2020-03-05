package com.application.entity;

import java.math.BigDecimal;

public class Amount {
	
	private BigDecimal value;
	private Currency currency;
	
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
