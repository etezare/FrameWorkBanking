package creditcard.model;

import creditcard.service.strategy.CreditCardMonthlyRoleStrategy;
import framework.model.Account;
import framework.model.AccountEntry;

import java.time.LocalDate;

public  class CreditCardAccount extends Account {

	private LocalDate expirationDate;

	private CreditCardMonthlyRoleStrategy creditCardMonthlyRoleStrategy;

	private String accountType;

	public CreditCardAccount(LocalDate expirationDate, String accountNumber,String accuntType) {
		super(accountNumber);
		this.expirationDate = expirationDate;
		this.accountType=accuntType;
	}

	public CreditCardAccount(String accountNumber,String accountType) {
		this(null, accountNumber, accountType);
	}

	//design pattern can be factory method for account entry;
	@Override
	public void deposit(double amount) {
		AccountEntry entry = new AccountEntry(LocalDate.now(),amount,"charged", getAccountNumber(), getCustomer().getName());
		getEntryList().add(entry);
	}

	@Override
	public void withdraw(double amount) {
		AccountEntry entry = new AccountEntry(LocalDate.now(),-amount,"paid",getAccountNumber(), getCustomer().getName());
		getEntryList().add(entry);
	}

	private void addEntry(AccountEntry entry) {
		getEntryList().add(entry);
	}

	public void addMonthlyInterest() {
		System.out.println("Account Number: " + getAccountNumber() + " Account Type: "+getAccountType() + " Account Balance: " + getBalance() + " Interest: " + creditCardMonthlyRoleStrategy.monthlyInterest(getBalance()));
		AccountEntry entry = new AccountEntry(LocalDate.now(), creditCardMonthlyRoleStrategy.monthlyInterest(getBalance()),
				"interest",
				getAccountNumber(),
				getCustomer().getName());
		getEntryList().add(entry);
		System.out.println("New Balance after adding interest: " + getBalance());
		System.out.println("-----------------------------------------------------------");
	}
	public void minimumPayment() {
		System.out.println("Account Number: " + getAccountNumber() + " Account Type: "+accountType+ " Account Balance: " + getBalance() + " Interest: " + creditCardMonthlyRoleStrategy.monthlyInterest(getBalance()));
		AccountEntry entry = new AccountEntry(LocalDate.now(), creditCardMonthlyRoleStrategy.minimumPayment(getBalance()),
				"interest",
				getAccountNumber(),
				getCustomer().getName());
		getEntryList().add(entry);
		System.out.println("New Balance after adding interest: " + getBalance());
		System.out.println("-----------------------------------------------------------");
	}

	public CreditCardMonthlyRoleStrategy getCreditCardMonthlyRole() {
		return creditCardMonthlyRoleStrategy;
	}

	public void setCreditCardMonthlyRole(CreditCardMonthlyRoleStrategy creditCardMonthlyRoleStrategy) {
		this.creditCardMonthlyRoleStrategy = creditCardMonthlyRoleStrategy;
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
