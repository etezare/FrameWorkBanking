package framework.service.template;

import framework.model.Account;
import framework.model.AccountEntry;
import framework.service.AccountServiceImpl;
import framework.util.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
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
                .filter(ae -> ae.getDate().compareTo(Utils.convertLocalDateToDate(reportDate)) < 0)
                .collect(Collectors.toList());

        this.prevBalance = accountEntries.stream().mapToDouble(x -> x.getAmount()).sum();
        System.out.println(String.format("Previous Balance: %s", prevBalance));
    }

    @Override
    void showTotalCredit(LocalDate reportDate) {
        List<AccountEntry> accountEntries = account.getEntryList()
                .stream()
                .filter(ae -> ae.getAmount() < 0)
                .collect(Collectors.toList());
        totalCredit = accountEntries.stream().mapToDouble(x -> x.getAmount()).sum();
        System.out.println(String.format("Total credit: %s", totalCredit));
    }

    @Override
    void showTotalDebit(LocalDate reportDate) {
        List<AccountEntry> accountEntries = account.getEntryList()
                .stream()
                .filter(ae -> ae.getAmount() >= 0)
                .collect(Collectors.toList());
        totalDebit = accountEntries.stream().mapToDouble(x -> x.getAmount()).sum();
        System.out.println(String.format("Total debit: %s", totalDebit));
    }

    @Override
    void showBalance(LocalDate reportDate) {
        List<AccountEntry> accountEntries = account.getEntryList()
                .stream()
                .filter(ae -> ae.getDate().compareTo(Utils.convertLocalDateToDate(reportDate)) == 0)
                .collect(Collectors.toList());

        balance = accountEntries.stream().mapToDouble(x -> x.getAmount()).sum();
        balance += prevBalance + totalDebit + totalCredit;

        System.out.println(String.format("Balance: %s", balance));
    }
}
