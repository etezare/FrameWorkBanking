package framework.service;

import framework.dao.AccountDAO;
import framework.model.Account;
import framework.model.AccountEntry;
import framework.model.Company;
import framework.model.Customer;
import framework.model.Personal;
import framework.service.factory.AccountFactory;
import framework.service.observer.EmailSender;
import framework.service.observer.Observer;
import framework.service.strategy.BankInterestStrategy;
import framework.service.strategy.InterestStrategy;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {
  private List<Observer<Account>> observerList = new ArrayList<>();

  private AccountDAO accountDAO = new AccountDAO();

  private CustomerService customerService = new CustomerServiceImpl();

  private InterestStrategy interestStrategy = new BankInterestStrategy();

  public AccountServiceImpl() {
    this.addObserver(new EmailSender());
  }

  @Override
  public List<Account> getList() {
    return accountDAO.getList();
  }

  @Override
  public Account createAccount(String accountNumber, String customerId, String type) {
    Account account = AccountFactory.createAccount(type);

    account.setAccountNumber(accountNumber);

    Customer customer = customerService.getCustomer(customerId);
    account.setCustomer(customer);

    accountDAO.add(account);
    return account;
  }

  @Override
  public Account getAccount(String accountNumber) {
    Account entity = accountDAO.getById(accountNumber);
    return entity;
  }

  @Override
  public List<Account> getAllAccounts() {
    return accountDAO.getList();
  }

  @Override
  public void deposit(String accountNumber, double amount) {
    Account account = accountDAO.getById(accountNumber);

    account.deposit(amount);
    accountDAO.update(account);
    notify(account, amount);
  }

  @Override
  public void withdraw(String accountNumber, double amount) {
    Account account = accountDAO.getById(accountNumber);

    account.withdraw(amount);
    accountDAO.update(account);
    notify(account, amount);
  }

  private void notify(Account account, double amount) {
    Customer c = account.getCustomer();

    if (c != null) {
      if (c instanceof Company){
        notifyObserver(account);
      } else if (c instanceof Personal) {
        if (amount > 500 || account.getBalance() >500) {
          notifyObserver(account);
        }
      }
    }
  }

  @Override
  public void addObserver(Observer observer) {
    observerList.add(observer);
  }

  @Override
  public void removeObservable(Observer observer) {
    observerList.remove(observer);
  }

  @Override
  public void notifyObserver(Account account) {
    for (Observer observer : observerList) {
      observer.update(account);
    }
  }

  @Override
  public void addInterest() {
    List<Account> accounts = accountDAO.getList();
    for (Account account : accounts) {
      double interest = interestStrategy.calculateInterest();

      AccountEntry entry = new AccountEntry(interest,
          "interest",
          account.getAccountNumber(),
          account.getCustomer().getName());
      account.getEntryList().add(entry);

      accountDAO.update(account);
    }
  }

}
