package framework.database;

import com.github.javafaker.Faker;
import framework.model.*;
import framework.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Accounts {
  public static List<Account> accounts = new ArrayList<>();

//  private Customer customer;
//  private String accountNumber;
//deposit
  //withdraw

  static {
    Faker faker = new Faker();

    for (Customer c : Customers.customers){
      //generating accounts
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
  }

}
