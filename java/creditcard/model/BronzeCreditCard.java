package creditcard.model;

public class BronzeCreditCard extends CreditCardAccount{
    private final String type="BRONZE";
    public BronzeCreditCard(String accountNumber) {
        super(accountNumber);
    }

    @Override
    public String getType() {
        return type;
    }

}
