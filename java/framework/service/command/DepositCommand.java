package framework.service.command;

import framework.service.AccountService;
import framework.service.AccountServiceImpl;

public class DepositCommand implements Command {

  private AccountService accountService = new AccountServiceImpl();

  @Override
  public void execute(String accountNumber, double amount) {
    accountService.deposit(accountNumber, amount);
  }
}
