package com.vasavibank.main.model;

public class BankDetails {
	private int customerId;
	private String bankStatus;
	private double initialDeposite;
	private String accountNumber;
	private  double accountBalance;
	
	private int bankId;
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getBankStatus() {
		return bankStatus;
	}
	public void setBankStatus(String bankStatus) {
		this.bankStatus = bankStatus;
	}
	public double getInitialDeposite() {
		return initialDeposite;
	}
	public void setInitialDeposite(double initialDeposite) {
		this.initialDeposite = initialDeposite;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	

}
