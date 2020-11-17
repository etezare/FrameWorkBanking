package creditcard.model;

import java.time.LocalDate;

public class AccountEntry {
	private LocalDate date;
	private double amount;
	private String description;
	private String fromAccountNumber;
	private String fromCutomerName;
	
	public AccountEntry(LocalDate now, double amount, String description, String accountNumber, String name) {
		this.date=now;
		this.amount=amount;
		this.description=description;
		this.fromAccountNumber=accountNumber;
		this.fromCutomerName=name;
	}



	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getFromCutomerName() {
		return fromCutomerName;
	}

	public void setFromCutomerName(String fromCutomerName) {
		this.fromCutomerName = fromCutomerName;
	}
}
