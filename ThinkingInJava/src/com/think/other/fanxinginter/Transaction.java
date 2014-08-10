package com.think.other.fanxinginter;

public abstract class Transaction implements Command{
	private TransactionException e;

	public TransactionException getE() {
		return e;
	}

	public void setE(TransactionException e) {
		this.e = e;
	}
	
}
