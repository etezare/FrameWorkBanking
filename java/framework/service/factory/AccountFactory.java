package framework.service.factory;

import framework.model.Account;
import framework.model.CheckingAccount;
import framework.model.SavingAccount;

public class AccountFactory {

  private static final String CHECKING = "checking";
  private static final String SAVING = "saving";
  private static final String CREDIT = "credit";

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
}
