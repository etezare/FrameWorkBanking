package framework.model;

public class Customer {
	private String customerId;
	private String name;

	public Customer(String customerId, String name) {
		this.name = name;
		this.customerId = customerId;
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
