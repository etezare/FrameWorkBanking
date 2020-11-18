package banking.service.command;

import banking.service.BankingAccountServiceImpl;
import framework.service.AccountService;
import framework.service.command.Command;

public class WithdrawCommand implements Command {

  private AccountService accountService = new BankingAccountServiceImpl();

  @Override
  public void execute(String accountNumber, double amount) {
    accountService.withdraw(accountNumber, amount);
  }
}
