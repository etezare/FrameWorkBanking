package creditcard.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	List<CreditCardAccount> creditCardAccountList=new ArrayList<>();
	private String name;
	private String email;
	private Address address;
	private String customerType;

	public Customer(String name, String email, Address address,String customerType) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.customerType=customerType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	public void addCreditCardAccount(CreditCardAccount creditCardAccount){
		creditCardAccountList.add(creditCardAccount);
	}
	public void removeCreditCardAccount(CreditCardAccount creditCardAccount){
		creditCardAccountList.remove(creditCardAccount);
	}
}
