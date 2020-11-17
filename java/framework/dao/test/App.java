package framework.dao.test;

import framework.dao.CustomerDAO;
import framework.dao.DAO;
import framework.model.Customer;

import java.util.Arrays;
import java.util.List;

public class App {
	public static void main(String[] args) {
		
		//Test data
		DAO data = new CustomerDAO();
		List<Customer> customers = data.getList();
		System.out.println((Arrays.toString(customers.toArray())));
		
		data.delete(customers.get(0).getCustomerId());
		System.out.println((Arrays.toString(customers.toArray())));
		
	}
}
