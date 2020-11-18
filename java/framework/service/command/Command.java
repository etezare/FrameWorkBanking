package framework.service.command;

public interface Command {
  void execute(String accountNumber, double amount);
}
