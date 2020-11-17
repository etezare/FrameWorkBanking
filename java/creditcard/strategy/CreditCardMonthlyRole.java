package creditcard.strategy;

public interface CreditCardMonthlyRole {
    double monthlyInterest(double balance);
    double minimumPayment(double balance);
}
