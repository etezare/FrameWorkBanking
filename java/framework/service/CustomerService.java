package framework.service;

import framework.model.Customer;

import java.util.Collection;

public interface CustomerService {
    Customer createCustomer(String customerId, String customerName);
    Customer getCustomer(String customerId);
    Collection<Customer> getAllCustomers();
}
