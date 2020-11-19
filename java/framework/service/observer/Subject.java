package framework.service.observer;

import framework.model.Account;

public interface Subject {
    void addObserver(Observer observer);

    void removeObservable(Observer observer);

    void notifyObserver(Account account);
}
