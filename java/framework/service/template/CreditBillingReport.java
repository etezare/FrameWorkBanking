package framework.service.template;

import creditcard.model.CreditCardAccount;
import creditcard.model.CustomerCredit;
import framework.model.Account;
import framework.model.AccountEntry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class CreditBillingReport extends MonthlyBillingReport{
    private Collection<Account>creditCardAccounts;
    private LocalDate lastMonth;
    public CreditBillingReport(Collection<Account> accounts) {
        this.creditCardAccounts=accounts;
        getLastMonth();

    }
    public void getLastMonth(){
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        c.add(Calendar.MONTH, -1);
        int month = c.get(Calendar.MONTH) + 1; // beware of month indexing from zero
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DATE) - 1;
        lastMonth = LocalDate.of(year, month, day);
    }

    public List<String> getBillingReport(){
        List<String> reports=new ArrayList<>();
        for(Account account:creditCardAccounts) {
            CreditCardAccount creditCardAccount = (CreditCardAccount) account;
            String report;
            Collection<AccountEntry> accountEntryList = creditCardAccount.getEntryList();

            double previousBalance =calculatePreviousBalance(creditCardAccount,accountEntryList);
            double totalCredits = calculateTotal("paid",accountEntryList);
            double totalCharges = calculateTotal("charged",accountEntryList);
            double newBalance =calculateNewBalance(previousBalance,totalCredits,totalCharges,creditCardAccount);
            double totalDue = calculateTotalDue(creditCardAccount,newBalance);
            System.out.println("----------billing Report--------------------------");
            CustomerCredit customer = (CustomerCredit) creditCardAccount.getCustomer();
            report="Name= " + customer.getName() + " address=" + customer.getAddress().toString() +
                    "  cc=" + creditCardAccount.getAccountNumber()
                    + " type=" + creditCardAccount.getAccountType()+"\n"+"previous balance " + previousBalance+
                    "\ntotal credits this month " + totalCredits+
                    "\n"+"total charges this month " + totalCharges+"\n"+
                    "new balance " + newBalance+"\n"+
                    "total due this month " + totalDue ;
            System.out.println(report);
            reports.add(report);
            System.out.println("-------------------------------------------------");
        }
        return reports;
    }
    public double calculateTotal(String decsription, Collection<AccountEntry>accountEntryList){

        double total=0;

        for (AccountEntry accountEntry : accountEntryList) {
            if (accountEntry.getDate().compareTo(lastMonth) > 0) {
                if (accountEntry.getDescription().equalsIgnoreCase(decsription)) {
                    total += accountEntry.getAmount();
                }
            }
        }
        return total;
    }
    public double calculatePreviousBalance(CreditCardAccount creditCardAccount,Collection<AccountEntry>accountEntries){
        double previousBalance=creditCardAccount.getBalance();

        for (AccountEntry accountEntry : accountEntries) {
            if (accountEntry.getDate().compareTo(lastMonth) > 0) {
                previousBalance -= accountEntry.getAmount();
            }
        }
        return previousBalance;
    }

    public double calculateNewBalance(double previousBalance,double totalCredits,double totalCharges,CreditCardAccount creditCardAccount){
        return  previousBalance - totalCredits + totalCharges +
            (creditCardAccount.getCreditCardMonthlyRole().monthlyInterest((previousBalance - totalCredits)));
    }

    public double calculateTotalDue(CreditCardAccount creditCardAccount,double newBalance){
        return creditCardAccount.getCreditCardMonthlyRole().monthlyInterest(newBalance);
    }


    @Override
    double calcPreviousBalance(LocalDate reportDate) {
        System.out.println(String.format("Credit Card Billing Report - As of " + LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
        return 0;
    }

    @Override
    double calcTotalCredit(LocalDate reportDate) {
        return 0;
    }

    @Override
    double calcTotalDebit(LocalDate reportDate) {
        return 0;
    }

    @Override
    double calcBalance(LocalDate reportDate) {

        return 0;
    }

}
