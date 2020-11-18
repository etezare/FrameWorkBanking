package creditcard.service.command;

import creditcard.service.CreditAccountServiceImpl;
import framework.service.AccountService;
import framework.service.command.Command;

public class CreditWithdrawCommand implements Command {

  private AccountService accountService = new CreditAccountServiceImpl();

  @Override
  public void execute(String accountNumber, double amount) {
    accountService.withdraw(accountNumber, amount);
  }
}
