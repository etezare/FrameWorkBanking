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
    public void billingReport(String accountNumber){
        Collection<CreditCardAccount>accounts = accountDAO.getAccounts();
        for(CreditCardAccount creditCardAccount:accounts) {
            Collection<AccountEntry> accountEntryList = creditCardAccount.getEntryList();
            BillingReport billingReport = new BillingReport();
            billingReport.getBillingReport(accountEntryList);
            Date dt = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, 1);
            dt = c.getTime();
            c.add(Calendar.MONTH, -1);
            int month = c.get(Calendar.MONTH) + 1; // beware of month indexing from zero
            int year = c.get(Calendar.YEAR);
            int day = c.get(Calendar.DATE) - 1;
            LocalDate lastMonth = LocalDate.of(year, month, day);
            double previousBalance = creditCardAccount.getBalance();
            for (AccountEntry accountEntry : accountEntryList) {
                if (accountEntry.getDate().compareTo(lastMonth) > 0) {
                    previousBalance -= accountEntry.getAmount();
                }
            }
            double totalCredits = 0;
            double totalCharges = 0;

            for (AccountEntry accountEntry : accountEntryList) {
                if (accountEntry.getDate().compareTo(lastMonth) > 0) {
                    if (accountEntry.getDescription().equalsIgnoreCase("paid")) {
                        totalCredits += accountEntry.getAmount();
                    }
                }
            }
            for (AccountEntry accountEntry : accountEntryList) {
                if (accountEntry.getDate().compareTo(lastMonth) > 0) {
                    if (accountEntry.getDescription().equalsIgnoreCase("charged")) {
                        totalCharges += accountEntry.getAmount();
                    }
                }
            }
            double newBalance = previousBalance - totalCredits + totalCharges + (creditCardAccount.getCreditCardMonthlyRole().monthlyInterest((previousBalance - totalCredits)));
            double totalDue = creditCardAccount.getCreditCardMonthlyRole().monthlyInterest(newBalance);
            System.out.println("----------billing Report--------------------------");
            Customer customer=creditCardAccount.getCustomer();
            System.out.println("Name= "+customer.getName()+" address="+customer.getAddress().toString()+"  cc="+creditCardAccount.getAccountNumber()+" type="+creditCardAccount.getAccountType());
            System.out.println("previous balance " + previousBalance);
            System.out.println("total credits this month " + totalCredits);
            System.out.println("total charges this month " + totalCharges);
            System.out.println("new balance " + newBalance);
            System.out.println("total due this month " + totalDue);

            System.out.println("-------------------------------------------------");
        }
    }

}
