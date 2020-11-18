package framework.service;

import framework.dao.CustomerDAO;
import framework.model.Company;
import framework.model.Customer;
import framework.model.Personal;
import framework.service.factory.CustomerFactory;
import framework.util.Utils;

import java.util.Collection;

public class CustomerServiceImpl implements CustomerService {
	private static final String COMPANY = Utils.COMPANY;
	private static final String PERSONAL = Utils.PERSONAL;

	private CustomerDAO customerDAO;
	
	public CustomerServiceImpl(){
		customerDAO = new CustomerDAO();
	}

	@Override
	public Customer createCustomer(String customerId, String customerName, String type) {
		Customer cus = CustomerFactory.create(type);

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
