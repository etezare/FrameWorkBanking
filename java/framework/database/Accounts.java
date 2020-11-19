package framework.database;

import com.github.javafaker.Faker;
import creditcard.model.CreditCardAccount;
import creditcard.model.CustomerCredit;
import framework.model.*;
import framework.service.factory.AccountFactory;
import framework.service.factory.CustomerFactory;
import framework.util.DateUtils;
import framework.util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Accounts {
  private Accounts(){}

  public static List<Account> data = getInstance().getAccounts();

  private volatile static Accounts instance;

  private List<Account> accounts = new ArrayList<>();

  private static Accounts getInstance() {
    if (instance == null) {
      synchronized (Accounts.class) {
        if (instance == null) {
          instance = new Accounts();
        }
      }
    }
    return instance;
  }


  private List<Account> getAccounts() {
    fakeDataGeneration();
    return accounts;
  }

  private static final String GOLD = "gold";
  private static final String SILVER = "silver";
  private static final String BRONZE = "bronze";

  private void fakeDataGeneration(){
    Faker faker = new Faker();

    for (Customer c : Customers.data){
      Account account;
      if (Integer.valueOf(c.getCustomerId()) % 2 == 0)
        account = new CheckingAccount(faker.code().isbn10());
      else
        account = new SavingAccount(faker.code().isbn10());

      account.deposit((double) Utils.randInt(100, 1000));
      int numTransaction = Utils.randInt(3, 5);
      for (int i = 0; i < numTransaction; i++){
        if (Utils.randInt(1, 10) > 5){
          account.withdraw(Utils.randInt(5, 10));
        }
        else
        {
          account.deposit(Utils.randInt(5, 10));
        }
      }

      account.setCustomer(c);
      c.getAccountList().add(account);

      accounts.add(account);
    }

//Credit account
    for (int i = 0; i < 10; i++) {
      Address address = new Address(
              faker.address().fullAddress(),
              faker.address().state(),
              Utils.randInt(5000, 9000),
              faker.address().cityName());

      CustomerCredit customer = new CustomerCredit(
              faker.name().fullName(),
              faker.animal().name() + "@gmail.com",
              address,
              CustomerFactory.PERSONAL);
      customer.setCustomerId(faker.idNumber().invalidSvSeSsn());
      String accType = GOLD;
      if (Utils.randInt(1, 5) / 3 == 0)
        accType = GOLD;
      else if (Utils.randInt(1, 5) / 3 == 1)
        accType = SILVER;
      else
        accType = BRONZE;
      String accNum = faker.idNumber().ssnValid();

      Account creditAccount = AccountFactory.createCreditAccount(accNum, customer, accType);
      CreditCardAccount creditCardAccount = (CreditCardAccount) creditAccount;
      creditCardAccount.setExpirationDate(DateUtils.convertToLocalDateViaInstant(faker.date().between(org.apache.commons.lang3.time.DateUtils.addDays(new Date(), -30), new Date())));

      creditCardAccount.setCustomer(customer);
      creditCardAccount.deposit(Utils.randInt(500, 1000));
      customer.getAccountList().add(creditCardAccount);

      accounts.add(creditCardAccount);
    }
  }

}
