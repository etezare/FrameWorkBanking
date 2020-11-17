package creditcard.model;

public class SilverCreditCard extends CreditCardAccount {
    private final String type="SILVER";

    public SilverCreditCard(String accountNumber) {
        super(accountNumber);

    }

    @Override
    public String getType() {
        return type;
    }

}
