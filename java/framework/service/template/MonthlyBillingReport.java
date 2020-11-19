package framework.service.template;

import framework.model.Account;
import framework.service.AccountService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    abstract double calcPreviousBalance(LocalDate reportDate);
    abstract double calcTotalCredit(LocalDate reportDate);
    abstract double calcTotalDebit(LocalDate reportDate);
    abstract double calcBalance(LocalDate reportDate);

    void hook(){

    }
    public void printSummary(){
        System.out.println("-----------------Summary Type Report-----------------");
        System.out.println(String.format("Summary: Previous Balance: %s, Total Credit %s, Total Debit %s, Current Balance %s", calcPreviousBalance(reportDate), calcTotalCredit(reportDate), calcTotalDebit(reportDate), calcBalance(reportDate)));
        System.out.println("-----------------End of Summary Type Report-----------------");
    }

    public void showReport(){
        System.out.println(String.format("\n**** Report Date: %s for cardnumber %s ****", reportDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")), account.getAccountNumber()));
        this.calcPreviousBalance(reportDate);
        this.calcTotalCredit(reportDate);
        this.calcTotalDebit(reportDate);
        this.calcBalance(reportDate);
        this.hook();
    }

    public String getReportString() {
        showReport();
        return reportString;
    }

    protected LocalDate getFirstDayOfReportMonth(){
        return reportDate.withDayOfMonth(1);
    }
    protected LocalDate getLastDayOfReportMonth(){
        return reportDate.withDayOfMonth(reportDate.lengthOfMonth());
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
