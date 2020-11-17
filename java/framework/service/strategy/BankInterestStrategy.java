package framework.service.strategy;

public class BankInterestStrategy implements InterestStrategy {
  @Override
  public double calculateInterest() {
    return 1;
  }
}
