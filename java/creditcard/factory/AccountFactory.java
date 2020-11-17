package creditcard.factory;

import creditcard.model.BronzeCreditCard;
import creditcard.model.CreditCardAccount;
import creditcard.model.GoldCreditCard;
import creditcard.model.SilverCreditCard;

public class AccountFactory {
    public CreditCardAccount createAccount(String accountNumber,String type) {
        if(type.equalsIgnoreCase("silver")){
            return new SilverCreditCard(accountNumber);
        }else if(type.equalsIgnoreCase("gold")){
            return new GoldCreditCard(accountNumber);
        }else if(type.equalsIgnoreCase("bronze")){
            return new BronzeCreditCard(accountNumber);
        }else return null;
    }
}
