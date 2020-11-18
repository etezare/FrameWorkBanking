package framework.service.command;

import banking.service.BankingAccountServiceImpl;
import framework.service.AccountService;

public class DepositCommand implements Command {

  private AccountService accountService = new BankingAccountServiceImpl();

  @Override
  public void execute(String accountNumber, double amount) {
    accountService.deposit(accountNumber, amount);
  }
}
