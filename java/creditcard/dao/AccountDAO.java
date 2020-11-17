package creditcard.dao;

import creditcard.model.CreditCardAccount;

import java.util.Collection;

public interface AccountDAO {
	void saveAccount(CreditCardAccount creditCardAccount);
	void updateAccount(CreditCardAccount creditCardAccount);
	CreditCardAccount loadAccount(String accountnumber);
	Collection<CreditCardAccount> getAccounts();
}
