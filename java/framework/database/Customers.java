package framework.database;

import com.github.javafaker.Faker;
import framework.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class Customers {
  public static List<Customer> customers = new ArrayList<>();

  static {
    Faker faker = new Faker();
    for (int i = 0; i < 50; i++)
    {
      String customerId 	= String.format("%05d", i);
      String customerName = faker.name().fullName();
      customers.add(new Customer(customerId, customerName));
    }
  }

}
