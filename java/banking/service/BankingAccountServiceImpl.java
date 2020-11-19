package banking.service;

import creditcard.model.CreditCardAccount;
import framework.dao.AccountDAO;
import framework.model.Account;
import framework.model.AccountEntry;
import framework.model.Customer;
import framework.service.CustomerService;
import framework.service.CustomerServiceImpl;
import framework.service.factory.AccountFactory;
import framework.service.observer.EmailSender;
import framework.service.observer.Observer;
import framework.service.observer.Subject;
import framework.service.strategy.BankInterestStrategy;
import framework.service.strategy.InterestStrategy;
import framework.service.template.AccountMonthlyBillingReport;
import framework.service.template.MonthlyBillingReport;
import framework.util.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankingAccountServiceImpl implements Subject, BankingAccountService {
  private List<Observer<Account>> observerList = new ArrayList<>();

  private AccountDAO accountDAO = new AccountDAO();

  private CustomerService customerService = new CustomerServiceImpl();

  private InterestStrategy interestStrategy = new BankInterestStrategy();

  public BankingAccountServiceImpl() {
    this.addObserver(new EmailSender());
  }

  @Override
  public List<Account> getList() {
    return accountDAO.getList();
  }

  @Override
  public Account createAccount(String accountNumber, String customerId, String type, String customerType) {
    Account account = AccountFactory.createAccount(type);

    account.setAccountNumber(accountNumber);

    Customer customer = customerService.getCustomer(customerId);

    if (customer == null) {
      customer = customerService.createCustomer(customerId, "No Name", customerType);
    }

    customer.getAccountList().add(account);
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
    Customer customer = account.getCustomer();

    if (customer != null) {
      if (customer.getType().equalsIgnoreCase(Utils.COMPANY) ||
          (customer.getType().equalsIgnoreCase(Utils.PERSONAL)
              && (amount > 500 || account.getBalance() < 0))) {
        notifyObserver(account);
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

  @Override
  public List<String> billingReport() {
    List<Account> accountList = getList();
    List<String> stringList = new ArrayList<>();
    for (Account account : accountList){
      if (!(account instanceof CreditCardAccount)) {
        MonthlyBillingReport mbr = new AccountMonthlyBillingReport(account, LocalDate.now());
        mbr.printSummary();
        stringList.add(mbr.getReportString());
      }
    }

    return stringList;
  }
}
