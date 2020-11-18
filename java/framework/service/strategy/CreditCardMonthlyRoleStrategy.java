package framework.service.strategy;

public interface CreditCardMonthlyRoleStrategy {
    double monthlyInterest(double balance);
    double minimumPayment(double balance);
}
