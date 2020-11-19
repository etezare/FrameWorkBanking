package framework.service.template;

import framework.model.Account;
import framework.service.AccountService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

abstract public class MonthlyBillingReport {
    protected AccountService accountService;
    protected String reportString = "";
    protected Account account;

    private LocalDate reportDate = LocalDate.now();

    public MonthlyBillingReport(){
        this.reportDate = LocalDate.now();
    }

    public MonthlyBillingReport(Account account, LocalDate reportDate){
        this.reportDate = reportDate;
        this.account = account;
    }
    abstract void showPreviousBalance(LocalDate reportDate);
    abstract void showTotalCredit(LocalDate reportDate);
    abstract void showTotalDebit(LocalDate reportDate);
    abstract void showBalance(LocalDate reportDate);

    void hook(){

    }

    public void showReport(){
        System.out.println("Report Date: " + reportDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        this.showPreviousBalance(reportDate);
        this.showTotalCredit(reportDate);
        this.showTotalDebit(reportDate);
        this.showBalance(reportDate);
        this.hook();
    }

    public String getReportString() {
        showReport();
        return reportString;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public AccountService getAccountService() {
        return accountService;
    }
}
