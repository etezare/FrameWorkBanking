package framework.service.template;

import creditcard.model.CreditCardAccount;
import creditcard.model.CustomerCredit;
import framework.model.Account;
import framework.model.AccountEntry;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CreditMonthlyBillingReport extends MonthlyBillingReport {

    double prevBalance = 0;
    double totalCredit = 0;
    double totalDebit = 0;
    double balance = 0;
    public CreditMonthlyBillingReport(Account account, LocalDate reportDate) {
        super(account, reportDate);
    }

    @Override
    double calcPreviousBalance(LocalDate reportDate) {
        List<AccountEntry> accountEntries = account.getEntryList()
                .stream()
                .filter(ae -> ae.getDate().compareTo(getFirstDayOfReportMonth()) < 0)
                .collect(Collectors.toList());

        prevBalance = 0;
        prevBalance = accountEntries.stream().mapToDouble(x -> x.getAmount()).sum();
        return prevBalance;
    }

    @Override
    double calcTotalCredit(LocalDate reportDate) {
        List<AccountEntry> accountEntries = account.getEntryList()
                .stream()
                .filter(ae -> ae.getDescription().equals("paid"))
                .filter(ae -> ae.getDate().compareTo(getFirstDayOfReportMonth()) >= 0 && ae.getDate().compareTo(getLastDayOfReportMonth()) <= 0)
                .collect(Collectors.toList());
        totalCredit = 0;
        totalCredit = accountEntries.stream().mapToDouble(x -> x.getAmount()).sum();

        return totalCredit;
    }

    @Override
    double calcTotalDebit(LocalDate reportDate) {
        List<AccountEntry> accountEntries = account.getEntryList()
                .stream()
                .filter(ae -> ae.getDescription().equals("charged"))
                .filter(ae -> ae.getDate().compareTo(getFirstDayOfReportMonth()) >= 0 && ae.getDate().compareTo(getLastDayOfReportMonth()) <= 0)
                .collect(Collectors.toList());
        totalDebit = 0;
        totalDebit = accountEntries.stream().mapToDouble(x -> x.getAmount()).sum();

        return totalDebit;
    }

    @Override
    double calcBalance(LocalDate reportDate) {
        double newBalance =calculateNewBalance(prevBalance,totalCredit,totalDebit,(CreditCardAccount) account);
        double totalDue = calculateTotalDue((CreditCardAccount) account,newBalance);

        balance = prevBalance + totalDebit + totalCredit;

        String reportLine = String.format("Balance: %s", balance);
        reportString += reportLine;

        System.out.println(reportLine);

        System.out.println("----------billing Report--------------------------");
        CustomerCredit customer = (CustomerCredit) account.getCustomer();
        CreditCardAccount creditCardAccount = ((CreditCardAccount)account);
        String report = "Name= " + customer.getName() + " address=" + customer.getAddress().toString() +
                "  cc=" + account.getAccountNumber()
                + " type=" + creditCardAccount.getAccountType()+"\n"+"previous balance " + prevBalance +
                "\ntotal credits this month " + totalCredit +
                "\n"+"total charges this month " + totalDebit + "\n"+
                "new balance " + newBalance+"\n"+
                "total due this month " + totalDue ;
        System.out.println(report);
        reportString = report;
        System.out.println("-------------------------------------------------");

        return balance;
    }

    public double calculateNewBalance(double previousBalance, double totalCredits, double totalCharges, CreditCardAccount creditCardAccount){
        return  previousBalance - totalCredits + totalCharges +
                (creditCardAccount.getCreditCardMonthlyRole().monthlyInterest((previousBalance - totalCredits)));
    }
    public double calculateTotalDue(CreditCardAccount creditCardAccount,double newBalance){
        return creditCardAccount.getCreditCardMonthlyRole().monthlyInterest(newBalance);
    }
}
