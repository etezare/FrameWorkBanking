package framework.service.factory;

import framework.model.Account;
import framework.model.CheckingAccount;
import framework.model.CreditCardAccount;
import framework.model.CustomerCredit;
import framework.model.SavingAccount;
import framework.service.strategy.CreditBronzeMonthlyRoleStrategy;
import framework.service.strategy.CreditGoldMonthlyRoleStrategy;
import framework.service.strategy.CreditSilverMonthlyRoleStrategy;

public class AccountFactory {

  public static final String CHECKING = "checking";
  public static final String SAVING = "saving";
  public static final String CREDIT = "credit";

  public static final String GOLD = "gold";
  public static final String SILVER = "silver";
  public static final String BRONZE = "bronze";

  public static Account createAccount(String type) {
    if (CHECKING.equals(type)) {
      return new CheckingAccount();
    } else if (SAVING.equals(type)) {
      return new SavingAccount();
    } else if (CREDIT.equals(type)) {
      return null;
    }
    return null;
  }

  public static CreditCardAccount createCreditAccount(String accountNumber, CustomerCredit customer, String type) {
    CreditCardAccount creditCardAccount = new CreditCardAccount(accountNumber, type);
    creditCardAccount.setCustomer(customer);
    if (type.equalsIgnoreCase(GOLD)) {
      creditCardAccount.setCreditCardMonthlyRole(new CreditGoldMonthlyRoleStrategy());
    }
    if (type.equalsIgnoreCase(SILVER)) {
      creditCardAccount.setCreditCardMonthlyRole(new CreditSilverMonthlyRoleStrategy());
    }
    if (type.equalsIgnoreCase(BRONZE)) {
      creditCardAccount.setCreditCardMonthlyRole(new CreditBronzeMonthlyRoleStrategy());
    }
    return creditCardAccount;
  }
}
