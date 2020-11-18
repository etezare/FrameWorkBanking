package framework.service;

import framework.model.Account;
import framework.model.CustomerCredit;
import framework.service.observer.Observer;

import java.util.List;

public interface CreditAccountService {

    Account getAccount(String accountNumber);

    List<Account> getAllAccounts();

    Account createAccount(String accountNumber, CustomerCredit customer, String type);

    void deposit (String accountNumber, double amount);
    void withdraw (String accountNumber, double amount);
    void addObserver(Observer observer);

    void removeObservable(Observer observer);

    void notifyObserver(Account creditCardAccount);

    void addInterest();

    void minimumPaid();

    List<String> billingReport();
}
