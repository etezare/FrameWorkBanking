package framework.service;


import framework.model.Account;
import framework.service.observer.Observer;

import java.util.List;

public interface AccountService {
    List<Account> getList();

    Account getAccount(String accountNumber);

    void deposit (String accountNumber, double amount);

    void withdraw (String accountNumber, double amount);

    void addObserver(Observer observer);

    void removeObservable(Observer observer);

    void notifyObserver(Account account);

    void addInterest();
}
