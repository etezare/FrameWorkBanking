package framework.database;

import com.github.javafaker.Faker;
import framework.model.Company;
import framework.model.Customer;
import framework.model.Personal;

import java.util.ArrayList;
import java.util.List;

public class Customers {
  private Customers(){}

  public static List<Customer> data = getInstance().getCustomers();

  private volatile static Customers cusInstance;

  private List<Customer> customers = new ArrayList<>();

  private static Customers getInstance() {
    if (cusInstance == null) {
      synchronized (Customers.class) {
        if (cusInstance == null) {
          cusInstance = new Customers();
        }
      }
    }
    return cusInstance;
  }

  private List<Customer> getCustomers() {
    fakeDataGeneration();
    return customers;
  }

  private void fakeDataGeneration(){
    Faker faker = new Faker();
    for (int i = 0; i < 10; i++) {
      String customerId 	= String.format("%05d", i);
      String customerName = faker.name().fullName();
      Customer c = null;
      if (i % 2 == 0) {
        c = new Company();
      } else {
        c = new Personal();
      }

      c.setCustomerId(customerId);
      c.setName(customerName);
      if (i % 2 == 0) {
        c.setType("Company");
      } else {
        c.setType("Personal");
      }
      c.setEmail("etezare@miu.edu");
      customers.add(c);
    }
  }

}
