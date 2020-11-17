package framework.database;

import com.github.javafaker.Faker;
import framework.model.Company;
import framework.model.Customer;
import framework.model.Personal;

import java.util.ArrayList;
import java.util.List;

public class Customers {
  public static List<Customer> customers = new ArrayList<>();

  static {
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
      customers.add(c);
    }
  }

}
