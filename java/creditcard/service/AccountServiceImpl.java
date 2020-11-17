package creditcard.service;

import creditcard.dao.AccountDAO;
import creditcard.dao.AccountDAOImpl;
import creditcard.factory.AccountFactory;
import creditcard.model.AccountEntry;
import creditcard.model.CreditCardAccount;
import creditcard.model.Customer;
import creditcard.observer.EmailSender;
import creditcard.observer.Observer;
import creditcard.strategy.BronzeMonthlyRole;
import creditcard.strategy.GoldMonthlyRole;
import creditcard.strategy.SilverMonthlyRole;

import java.time.LocalDate;
import java.util.*;

public class AccountServiceImpl implements AccountService {
    Collection<Observer> observerList = new ArrayList<>();
    private AccountDAO accountDAO;
    private AccountFactory accountFactory = new AccountFactory();

    public AccountServiceImpl() {
        accountDAO = new AccountDAOImpl();
        this.addObserver(new EmailSender());
    }

    @Override
    public CreditCardAccount createAccount(String accountNumber, Customer customer, String type) {
        CreditCardAccount creditCardAccount = accountFactory.createAccount(accountNumber, type);
        customer.addCreditCardAccount(creditCardAccount);
        if (type.equalsIgnoreCase("gold")) {
            creditCardAccount.setCreditCardMonthlyRole(new GoldMonthlyRole());
        }
        if (type.equalsIgnoreCase("silver")) {
            creditCardAccount.setCreditCardMonthlyRole(new SilverMonthlyRole());
        }
        if (type.equalsIgnoreCase("bronze")) {
            creditCardAccount.setCreditCardMonthlyRole(new BronzeMonthlyRole());
        }
        creditCardAccount.setCustomer(customer);
        accountDAO.saveAccount(creditCardAccount);

        return creditCardAccount;
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        CreditCardAccount creditCardAccount = accountDAO.loadAccount(accountNumber);
        creditCardAccount.deposit(amount);
        if (amount > 400) {
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
        if (amount > 400) {
            notifyObserver(creditCardAccount);
        }
        accountDAO.updateAccount(creditCardAccount);
    }


    //	public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) {
//		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
//		Account toAccount = accountDAO.loadAccount(toAccountNumber);
//		fromAccount.transferFunds(toAccount,fromAccount, amount, description);
//		notifyObserver(fromAccount);
//		notifyObserver(toAccount);
//		accountDAO.updateAccount(fromAccount);
//		accountDAO.updateAccount(toAccount);
//	}
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
        CreditCardAccount creditCardAccount = accountDAO.loadAccount(accountNumber);
        Collection<AccountEntry> accountEntryList=creditCardAccount.getEntryList();
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        c.add(Calendar.MONTH, -1);
        int month = c.get(Calendar.MONTH) + 1; // beware of month indexing from zero
        int year  = c.get(Calendar.YEAR);
        int day=c.get(Calendar.DATE)-1;
        LocalDate lastMonth=LocalDate.of(year,month,day);
        for(AccountEntry accountEntry:accountEntryList){
            if(accountEntry.getDate().equals(LocalDate.now())){
                System.out.println(accountEntry.getAmount());
            }
        }
        System.out.println(day);
    }

}
