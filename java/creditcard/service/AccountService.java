package creditcard.service;

import creditcard.model.CreditCardAccount;
import creditcard.model.Customer;
import creditcard.observer.Observer;

import java.util.Collection;

public interface AccountService {

    CreditCardAccount getAccount(String accountNumber);
    Collection<CreditCardAccount> getAllAccounts();


    CreditCardAccount createAccount(String accountNumber, Customer customer, String type);

    void deposit (String accountNumber, double amount);
    void withdraw (String accountNumber, double amount);
//    void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description);
    void addObserver(Observer observer);

    void removeObservable(Observer observer);

    void notifyObserver(CreditCardAccount creditCardAccount);

    void addInterest();

    void minimumPaid();

    void billingReport(String accountNumber);
}
