package creditcard.service.strategy;

public class CreditBronzeMonthlyRoleStrategy implements CreditCardMonthlyRoleStrategy {
    @Override
    public double monthlyInterest(double balance) {
        return 0.1*balance;
    }

    @Override
    public double minimumPayment(double balance) {
        return 0.14*balance;
    }
}
