package creditcard.service;

import creditcard.dao.CreditAccountDAO;
import creditcard.model.CreditCardAccount;
import creditcard.model.CustomerCredit;
import framework.model.Account;
import framework.model.Address;
import framework.model.Customer;
import framework.service.factory.AccountFactory;
import framework.service.observer.EmailSender;
import framework.service.observer.Observer;
import framework.service.template.CreditBillingReport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CreditAccountServiceImpl implements CreditAccountService {
    Collection<Observer> observerList = new ArrayList<>();
    private CreditAccountDAO accountDAO;
    public CreditAccountServiceImpl() {
        accountDAO = new CreditAccountDAO();
        addObserver(new EmailSender());
    }

    @Override
    public CreditCardAccount createAccount(String accountNumber, String type,String customerName,String email
            ,String customertype,String city,String state,String street,long zipcode) {
        Address address=new Address(street,state,zipcode,city);
        CustomerCredit customer=new CustomerCredit(customerName,email,address,customertype);
        CreditCardAccount creditCardAccount = AccountFactory.createCreditAccount(accountNumber,customer,type);
        accountDAO.add(creditCardAccount);
        return creditCardAccount;
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        Account creditCardAccount = accountDAO.getById(accountNumber);
        CustomerCredit customer=(CustomerCredit)creditCardAccount.getCustomer();
        creditCardAccount.deposit(amount);
        if(customer.getType().equalsIgnoreCase("company")){
            notifyObserver(creditCardAccount);
        }
        if (amount > 400 && customer.getType().equalsIgnoreCase("personal")) {
            notifyObserver(creditCardAccount);
        }
        accountDAO.update(creditCardAccount);
    }


    @Override
    public Account getAccount(String accountNumber) {
        Account creditCardAccount = accountDAO.getById(accountNumber);
        return creditCardAccount;
    }

    @Override
    public List<Account> getList() {
        return accountDAO.getList();
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        Account creditCardAccount = accountDAO.getById(accountNumber);
        creditCardAccount.withdraw(amount);
        CustomerCredit customer=(CustomerCredit)creditCardAccount.getCustomer();
        if(customer.getType().equalsIgnoreCase("company")){
            notifyObserver(creditCardAccount);
        }
        if (amount > 400 && customer.getType().equalsIgnoreCase("personal")) {
            notifyObserver(creditCardAccount);
        }
        accountDAO.update(creditCardAccount);
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
    public void notifyObserver(Account creditCardAccount) {
        for (Observer observer : observerList) {
            observer.update(creditCardAccount);
        }
    }

    @Override
    public void addInterest() {
        List<Account> accounts = accountDAO.getList();
        for (Account account : accounts) {
            CreditCardAccount creditCardAccount = (CreditCardAccount) account;
            creditCardAccount.addMonthlyInterest();
            accountDAO.update(account);
        }
    }

    @Override
    public void minimumPaid() {
        List<Account> accounts = accountDAO.getList();
        for (Account account : accounts) {
            CreditCardAccount creditCardAccount = (CreditCardAccount) account;
            creditCardAccount.minimumPayment();
            accountDAO.update(account);
        }
    }
    @Override
    public List<String> billingReport() {
        List<Account> accounts = accountDAO.getList();
        CreditBillingReport billingReport = new CreditBillingReport(accounts);
        return billingReport.getBillingReport();
    }

}
