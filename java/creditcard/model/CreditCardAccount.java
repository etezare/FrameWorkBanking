package creditcard.model;

import creditcard.strategy.CreditCardMonthlyRole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public abstract class CreditCardAccount {
	private Customer customer;
	private LocalDate expirationDate;
	private String accountNumber;
	CreditCardMonthlyRole creditCardMonthlyRole;
	Collection<AccountEntry> entryList = new ArrayList<AccountEntry>();
//	private String accountType;

	public CreditCardAccount(LocalDate expirationDate, String accountNumber) {
		this.expirationDate = expirationDate;
		this.accountNumber = accountNumber;
	}

	public CreditCardAccount(String accountNumber) {
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

//	public void transferFunds(CreditCardAccount toCreditCardAccount, double amount, String description) {
//		AccountEntry fromEntry = new AccountEntry(-amount, description, toCreditCardAccount.getAccountNumber(),
//				toCreditCardAccount.getCustomer().getName());
//		AccountEntry toEntry = new AccountEntry(amount, description, toCreditCardAccount.getAccountNumber(),
//				toCreditCardAccount.getCustomer().getName());
//
//		entryList.add(fromEntry);
//
//		toCreditCardAccount.addEntry(toEntry);
//	}

	public void addMonthlyInterest() {
		System.out.println("Account Number: " + accountNumber + " Account Type: "+this.getType() + " Account Balance: " + getBalance() + " Interest: " + creditCardMonthlyRole.monthlyInterest(getBalance()));
		AccountEntry entry = new AccountEntry(LocalDate.now(), creditCardMonthlyRole.monthlyInterest(getBalance()),
				"interest",
				accountNumber,
				customer.getName());
		entryList.add(entry);
		System.out.println("New Balance after adding interest: " + getBalance());
		System.out.println("-----------------------------------------------------------");
	}
	public void minimumPayment() {
		System.out.println("Account Number: " + accountNumber + " Account Type: "+this.getType() + " Account Balance: " + getBalance() + " Interest: " + creditCardMonthlyRole.monthlyInterest(getBalance()));
		AccountEntry entry = new AccountEntry(LocalDate.now(), creditCardMonthlyRole.minimumPayment(getBalance()),
				"interest",
				accountNumber,
				customer.getName());
		entryList.add(entry);
		System.out.println("New Balance after adding interest: " + getBalance());
		System.out.println("-----------------------------------------------------------");
	}
//
//	public String getAccountType() {
//		return accountType;
//	}
//
//	public void setAccountType(String accountType) {
//		this.accountType = accountType;
//	}


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
		this.customer = customer;
	}

	public Collection<AccountEntry> getEntryList() {
		return entryList;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	public abstract String getType();

}
