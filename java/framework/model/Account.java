package framework.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Account {

	private Customer customer;

	private String accountNumber;

	private List<AccountEntry> entryList = new ArrayList<AccountEntry>();

	public  Account(){}
	public Account(String accountNumber){
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		double balance = 0;
		for (AccountEntry entry : entryList) {
			balance += entry.getAmount();
		}
		return balance;
	}

	public void deposit(double amount) {
		AccountEntry entry = new AccountEntry(amount, "deposit", accountNumber, customer == null ? "" : customer.getName());
		entryList.add(entry);
	}

	public void withdraw(double amount) {
		AccountEntry entry = new AccountEntry(-amount, "withdraw", accountNumber, customer == null ? "" : customer.getName());
		entryList.add(entry);
	}

	private void addEntry(AccountEntry entry) {
		entryList.add(entry);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<AccountEntry> getEntryList() {
		return entryList;
	}
}
