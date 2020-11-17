package creditcard.model;

public class GoldCreditCard extends CreditCardAccount{

    private  final String type="GOLD";
    public GoldCreditCard(String accountNumber) {
        super(accountNumber);
    }



    public String getType() {
        return type;
    }


}
