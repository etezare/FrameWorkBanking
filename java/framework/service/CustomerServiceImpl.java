package framework.service;

import framework.dao.CustomerDAO;
import framework.model.Company;
import framework.model.Customer;
import framework.model.Personal;

import java.util.Collection;

public class CustomerServiceImpl implements CustomerService {
	private static final String COMPANY = "company";
	private static final String PERSONAL = "personal";

	private CustomerDAO customerDAO;
	
	public CustomerServiceImpl(){
		customerDAO = new CustomerDAO();
	}

	@Override
	public Customer createCustomer(String customerId, String customerName, String type) {
		Customer cus = null;

		if (COMPANY.equals(type)) {
			cus = new Company();
		} else {
			cus = new Personal();
		}

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
