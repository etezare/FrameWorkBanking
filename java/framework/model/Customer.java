package framework.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	List<Account> accountList = new ArrayList<>();

	private String customerId;
	private String name;
	private String email;

	public List<Account> getAccountList() {
		return accountList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerId() {
		return customerId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Customer cus = (Customer) o;

		if (!customerId.equals(cus.getCustomerId())) {
			return false;
		}

		return true;
	}
	
	@Override
	public int hashCode() {
		return customerId.hashCode();

	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Customer: %s %s", this.customerId, this.name);
	}
}
