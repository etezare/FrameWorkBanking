package creditcard.commandpattern;

import creditcard.service.AccountService;

public class DepositCommand implements Command {
    private AccountService accountService;
    private double amount;
    private String accountNumber;
    DepositCommand(String accountNumber, double amount, AccountService accountService){
        this.accountNumber=accountNumber;
        this.amount=amount;
        this.accountService=accountService;
    }

    @Override
    public void excute() {
        accountService.deposit(accountNumber,amount);
    }

    @Override
    public void unExcute() {
        accountService.withdraw(accountNumber,amount);
    }
}
