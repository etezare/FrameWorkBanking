package creditcard.strategy;

public class GoldMonthlyRole implements CreditCardMonthlyRole {
    @Override
    public double monthlyInterest(double balance) {
    return 0.6*balance;
    }

    @Override
    public double minimumPayment(double balance) {
        return 0.1*balance;
    }
}
