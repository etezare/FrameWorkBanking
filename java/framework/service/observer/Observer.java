package framework.service.observer;

import creditcard.model.CreditCardAccount;

public interface Observer<T> {
    void update(T t);
}
