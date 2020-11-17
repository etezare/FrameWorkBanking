package framework.service.factory;

import creditcard.model.BronzeCreditCard;
import creditcard.model.CreditCardAccount;
import creditcard.model.GoldCreditCard;
import creditcard.model.SilverCreditCard;
import framework.model.Account;
import framework.model.CheckingAccount;
import framework.model.SavingAccount;

public class AccountFactory {

  public static final String CHECKING = "checking";
  public static final String SAVING = "saving";
  public static final String CREDIT = "credit";

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
