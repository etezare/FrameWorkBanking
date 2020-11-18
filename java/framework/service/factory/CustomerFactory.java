package framework.service.factory;

import framework.model.Account;
import framework.model.CheckingAccount;
import framework.model.Company;
import framework.model.Customer;
import framework.model.Personal;
import framework.model.SavingAccount;

public class CustomerFactory {

  public static final String COMPANY = "company";
  public static final String PERSONAL = "personal";

  public static Customer create(String type) {
    if (PERSONAL.equals(type)) {
      return new Personal();
    } else if (COMPANY.equals(type)) {
      return new Company();
    }
    return null;
  }
}
