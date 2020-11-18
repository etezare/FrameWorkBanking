package framework.service.strategy;


public class CreditSilverMonthlyRoleStrategy implements CreditCardMonthlyRoleStrategy {


    @Override
    public double monthlyInterest(double balance) {
        return 0.8*balance;
    }

    @Override
    public double minimumPayment(double balance) {
        return 0.12*balance;
    }
}
