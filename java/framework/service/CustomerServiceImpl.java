package framework.service;

import framework.dao.CustomerDAO;
import framework.model.Customer;

import java.util.Collection;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDAO customerDAO;
	
	public CustomerServiceImpl(){
		customerDAO = new CustomerDAO();
	}

	@Override
	public Customer createCustomer(String customerId, String customerName) {
		Customer cus = new Customer();

		cus.setCustomerId(customerId);
		cus.setName(customerName);
		customerDAO.add(cus);
		return cus;
	}

	@Override
	public Customer getCustomer(String customerId) {
		return customerDAO.getById(customerId);
	}

	@Override
	public Collection<Customer> getAllCustomers() {
		return customerDAO.getList();
	}

}
