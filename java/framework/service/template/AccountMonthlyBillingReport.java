package framework.service.template;

import framework.model.Account;
import framework.model.AccountEntry;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AccountMonthlyBillingReport extends MonthlyBillingReport {

    double prevBalance = 0;
    double totalCredit = 0;
    double totalDebit = 0;
    double balance = 0;
    public AccountMonthlyBillingReport(Account account, LocalDate reportDate) {
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
        String reportLine = String.format("Previous Balance: %s", prevBalance);
        reportString += reportLine;
        System.out.println(reportLine);

        return prevBalance;
    }

    @Override
    double calcTotalCredit(LocalDate reportDate) {
        List<AccountEntry> accountEntries = account.getEntryList()
                .stream()
                .filter(ae -> ae.getAmount() < 0)
                .filter(ae -> ae.getDate().compareTo(getFirstDayOfReportMonth()) >= 0 && ae.getDate().compareTo(getLastDayOfReportMonth()) <= 0)
                .collect(Collectors.toList());
        totalCredit = 0;
        totalCredit = accountEntries.stream().mapToDouble(x -> x.getAmount()).sum();
        String reportLine = String.format("Total credit: %s", totalCredit);
        reportString += reportLine;
        System.out.println(reportLine);

        return totalCredit;
    }

    @Override
    double calcTotalDebit(LocalDate reportDate) {
        List<AccountEntry> accountEntries = account.getEntryList()
                .stream()
                .filter(ae -> ae.getAmount() >= 0)
                .filter(ae -> ae.getDate().compareTo(getFirstDayOfReportMonth()) >= 0 && ae.getDate().compareTo(getLastDayOfReportMonth()) <= 0)
                .collect(Collectors.toList());
        totalDebit = 0;
        totalDebit = accountEntries.stream().mapToDouble(x -> x.getAmount()).sum();

        String reportLine = String.format("Total debit: %s", totalDebit);
        reportString += reportLine;
        System.out.println(reportLine);

        return totalDebit;
    }

    @Override
    double calcBalance(LocalDate reportDate) {
        balance = prevBalance + totalDebit + totalCredit;

        String reportLine = String.format("Balance: %s", balance);
        reportString += reportLine;

        System.out.println(reportLine);

        return balance;
    }
}
