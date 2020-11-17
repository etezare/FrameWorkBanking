package creditcard.factory;


import creditcard.model.CreditCardAccount;
import creditcard.model.Customer;
import creditcard.strategy.BronzeMonthlyRole;
import creditcard.strategy.GoldMonthlyRole;
import creditcard.strategy.SilverMonthlyRole;
//import framework.AbstractFactory;

public class AccountFactory {
    //    @Override
    public CreditCardAccount createAccount(String accountNumber, Customer customer, String type) {
        CreditCardAccount creditCardAccount = new CreditCardAccount(accountNumber, type);
        creditCardAccount.setCustomer(customer);
        if (type.equalsIgnoreCase("gold")) {
            creditCardAccount.setCreditCardMonthlyRole(new GoldMonthlyRole());
        }
        if (type.equalsIgnoreCase("silver")) {
            creditCardAccount.setCreditCardMonthlyRole(new SilverMonthlyRole());
        }
        if (type.equalsIgnoreCase("bronze")) {
            creditCardAccount.setCreditCardMonthlyRole(new BronzeMonthlyRole());
        }
        return creditCardAccount;
    }
}
