package creditcard.model;

import creditcard.strategy.CreditCardMonthlyRole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public  class CreditCardAccount {
	private Customer customer;
	private LocalDate expirationDate;
	private String accountNumber;
	private CreditCardMonthlyRole creditCardMonthlyRole;
	private Collection<AccountEntry> entryList = new ArrayList<AccountEntry>();
	private String accountType;

	public CreditCardAccount(LocalDate expirationDate, String accountNumber,String accuntType) {
		this.expirationDate = expirationDate;
		this.accountNumber = accountNumber;
		this.accountType=accuntType;
	}

	public CreditCardAccount(String accountNumber,String accountType) {
		this.accountNumber = accountNumber;
		this.accountType=accountType;
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

	//design pattern can be factory method for account entry;
	public void deposit(double amount) {
		AccountEntry entry = new AccountEntry(LocalDate.now(),amount,"charged",accountNumber,customer.getName());
		entryList.add(entry);
	}

	public void withdraw(double amount) {
		AccountEntry entry = new AccountEntry(LocalDate.now(),-amount,"paid",accountNumber,customer.getName());
		entryList.add(entry);
	}

	private void addEntry(AccountEntry entry) {
		entryList.add(entry);
	}

	public void addMonthlyInterest() {
		System.out.println("Account Number: " + accountNumber + " Account Type: "+getAccountType() + " Account Balance: " + getBalance() + " Interest: " + creditCardMonthlyRole.monthlyInterest(getBalance()));
		AccountEntry entry = new AccountEntry(LocalDate.now(), creditCardMonthlyRole.monthlyInterest(getBalance()),
				"interest",
				accountNumber,
				customer.getName());
		entryList.add(entry);
		System.out.println("New Balance after adding interest: " + getBalance());
		System.out.println("-----------------------------------------------------------");
	}
	public void minimumPayment() {
		System.out.println("Account Number: " + accountNumber + " Account Type: "+accountType+ " Account Balance: " + getBalance() + " Interest: " + creditCardMonthlyRole.monthlyInterest(getBalance()));
		AccountEntry entry = new AccountEntry(LocalDate.now(), creditCardMonthlyRole.minimumPayment(getBalance()),
				"interest",
				accountNumber,
				customer.getName());
		entryList.add(entry);
		System.out.println("New Balance after adding interest: " + getBalance());
		System.out.println("-----------------------------------------------------------");
	}

	public CreditCardMonthlyRole getCreditCardMonthlyRole() {
		return creditCardMonthlyRole;
	}

	public void setCreditCardMonthlyRole(CreditCardMonthlyRole creditCardMonthlyRole) {
		this.creditCardMonthlyRole = creditCardMonthlyRole;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		customer.addCreditCardAccount(this);
		this.customer = customer;
	}

	public Collection<AccountEntry> getEntryList() {
		return entryList;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

}
