package creditcard.service;

import creditcard.dao.AccountDAO;
import creditcard.dao.AccountDAOImpl;
import creditcard.factory.AccountFactory;
import creditcard.model.AccountEntry;
import creditcard.model.BillingReport;
import creditcard.model.CreditCardAccount;
import creditcard.model.Customer;
import creditcard.observer.EmailSender;
import creditcard.observer.Observer;

import java.time.LocalDate;
import java.util.*;

public class AccountServiceImpl implements AccountService {
    Collection<Observer> observerList = new ArrayList<>();
    private AccountDAO accountDAO;
    private AccountFactory accountFactory=new AccountFactory();
    public AccountServiceImpl() {
        accountDAO = new AccountDAOImpl();
        addObserver(new EmailSender());
    }

    @Override
    public CreditCardAccount createAccount(String accountNumber, Customer customer, String type) {
        CreditCardAccount creditCardAccount =accountFactory.createAccount(accountNumber,customer,type);
        accountDAO.saveAccount(creditCardAccount);
        return creditCardAccount;
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        CreditCardAccount creditCardAccount = accountDAO.loadAccount(accountNumber);
        Customer customer=creditCardAccount.getCustomer();
        creditCardAccount.deposit(amount);
        if(customer.getCustomerType().equalsIgnoreCase("company")){
            notifyObserver(creditCardAccount);
        }
        if (amount > 400 && customer.getCustomerType().equalsIgnoreCase("personal")) {
            notifyObserver(creditCardAccount);
        }
        accountDAO.updateAccount(creditCardAccount);
    }


    @Override
    public CreditCardAccount getAccount(String accountNumber) {
        CreditCardAccount creditCardAccount = accountDAO.loadAccount(accountNumber);
        return creditCardAccount;
    }

    @Override
    public Collection<CreditCardAccount> getAllAccounts() {
        return accountDAO.getAccounts();
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        CreditCardAccount creditCardAccount = accountDAO.loadAccount(accountNumber);
        creditCardAccount.withdraw(amount);
        Customer customer=creditCardAccount.getCustomer();
        if(customer.getCustomerType().equalsIgnoreCase("company")){
            notifyObserver(creditCardAccount);
        }
        if (amount > 400 && customer.getCustomerType().equalsIgnoreCase("personal")) {
            notifyObserver(creditCardAccount);
        }
        accountDAO.updateAccount(creditCardAccount);
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
    public void notifyObserver(CreditCardAccount creditCardAccount) {
        for (Observer observer : observerList) {
            observer.update(creditCardAccount);
        }
    }

    @Override
    public void addInterest() {
        Collection<CreditCardAccount> accounts = accountDAO.getAccounts();
        for (CreditCardAccount account : accounts) {
            account.addMonthlyInterest();
            accountDAO.updateAccount(account);
        }
    }

    @Override
    public void minimumPaid() {
        Collection<CreditCardAccount> accounts = accountDAO.getAccounts();
        for (CreditCardAccount account : accounts) {
            account.minimumPayment();
            accountDAO.updateAccount(account);
        }
    }
    @Override
    public List<String> billingReport() {
        Collection<CreditCardAccount> accounts = accountDAO.getAccounts();
        BillingReport billingReport = new BillingReport(accounts);
        return billingReport.getBillingReport();
    }

}
