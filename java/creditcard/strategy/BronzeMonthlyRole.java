package creditcard.strategy;

public class BronzeMonthlyRole implements CreditCardMonthlyRole {
    @Override
    public double monthlyInterest(double balance) {
        return 0.1*balance;
    }

    @Override
    public double minimumPayment(double balance) {
        return 0.14*balance;
    }
}
