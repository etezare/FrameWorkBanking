package creditcard.model;

import framework.model.Address;
import framework.model.Customer;

public class CustomerCredit extends Customer {
	
	private Address address;

	public CustomerCredit(String name, String email, Address address, String customerType) {
		this.address = address;
		super.setEmail(email);
		super.setName(name);
		super.setType(customerType);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	public void addCreditCardAccount(CreditCardAccount creditCardAccount){
		getAccountList().add(creditCardAccount);
	}
	public void removeCreditCardAccount(CreditCardAccount creditCardAccount){
		getAccountList().remove(creditCardAccount);
	}
}
