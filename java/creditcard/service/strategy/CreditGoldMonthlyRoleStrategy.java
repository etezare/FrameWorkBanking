package creditcard.service.strategy;

public class CreditGoldMonthlyRoleStrategy implements CreditCardMonthlyRoleStrategy {
    @Override
    public double monthlyInterest(double balance) {
    return 0.06*balance;
    }

    @Override
    public double minimumPayment(double balance) {
        return 0.1*balance;
    }
}
