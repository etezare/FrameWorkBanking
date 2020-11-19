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
    void showPreviousBalance(LocalDate reportDate) {
        List<AccountEntry> accountEntries = account.getEntryList()
                .stream()
                .filter(ae -> ae.getDate().compareTo(reportDate) < 0)
                .collect(Collectors.toList());

        this.prevBalance = accountEntries.stream().mapToDouble(x -> x.getAmount()).sum();
        String reportLine = String.format("Previous Balance: %s", prevBalance);
        reportString += reportLine;
        System.out.println(reportLine);
    }

    @Override
    void showTotalCredit(LocalDate reportDate) {
        List<AccountEntry> accountEntries = account.getEntryList()
                .stream()
                .filter(ae -> ae.getAmount() < 0)
                .collect(Collectors.toList());
        totalCredit = accountEntries.stream().mapToDouble(x -> x.getAmount()).sum();
        String reportLine = String.format("Total credit: %s\n", totalCredit);
        reportString += reportLine;
        System.out.println(reportLine);
    }

    @Override
    void showTotalDebit(LocalDate reportDate) {
        List<AccountEntry> accountEntries = account.getEntryList()
                .stream()
                .filter(ae -> ae.getAmount() >= 0)
                .collect(Collectors.toList());
        totalDebit = accountEntries.stream().mapToDouble(x -> x.getAmount()).sum();

        String reportLine = String.format("Total debit: %s\n", totalDebit);
        reportString += reportLine;
        System.out.println(reportLine);
    }

    @Override
    void showBalance(LocalDate reportDate) {
        List<AccountEntry> accountEntries = account.getEntryList()
                .stream()
                .filter(ae -> ae.getDate().compareTo(reportDate) == 0)
                .collect(Collectors.toList());

        balance = accountEntries.stream().mapToDouble(x -> x.getAmount()).sum();
        balance += prevBalance + totalDebit + totalCredit;

        String reportLine = String.format("Balance: %s", balance);
        reportString += reportLine;

        System.out.println(reportLine);
    }
}
