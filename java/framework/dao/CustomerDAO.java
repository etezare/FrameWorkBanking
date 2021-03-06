package framework.dao;

import framework.database.Customers;
import framework.model.Customer;

import java.util.List;

public class CustomerDAO implements DAO<Customer, String> {

	@Override
	public List<Customer> getList() {
		return Customers.data;
	}

	@Override
	public Customer getById(String id) {
		return Customers.data.stream()
				.filter(x -> x.getCustomerId().equals(id))
				.findFirst()
				.orElse(null);
	}

	@Override
	public void add(Customer customer) {
		Customers.data.add(customer);
	}

	@Override
	public void delete(String id) {
		Customer c = getById(id);
		Customers.data.remove(c);
	}

	@Override
	public boolean update(Customer customer) {
		Customer entity = getById(customer.getCustomerId());
		if (entity == null) {
			return false;
		}
		entity.setName(customer.getName());
		return true;
	}
}
