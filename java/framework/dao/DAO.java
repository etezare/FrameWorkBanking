package framework.dao;

import java.util.List;

public interface DAO<T, ID> {

  //Customer Data
  List<T> getList();

  T getById(ID id);

  void add(T t);

  void delete(ID id);

  boolean update(T t);

//
//  //Customer Data
//  List<Customer> getCustomerList();
//
//  Customer getCustomerById(String customerId);
//
//  void addCustomer(String customerId, String customerName);
//
//  void addCustomer(Customer customer);
//
//  void deleteCustomer(Customer customer);
//
//  boolean updateCustomer(Customer customer, String name);

  //End of Customer Data
}
