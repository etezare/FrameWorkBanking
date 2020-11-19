package creditcard.service.strategy;


public class CreditSilverMonthlyRoleStrategy implements CreditCardMonthlyRoleStrategy {


    @Override
    public double monthlyInterest(double balance) {
        return 0.08*balance;
    }

    @Override
    public double minimumPayment(double balance) {
        return 0.12*balance;
    }
}
