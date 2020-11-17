package creditcard.observer;

import creditcard.model.CreditCardAccount;

public interface Observer {
    void update(CreditCardAccount creditCardAccount);
}
