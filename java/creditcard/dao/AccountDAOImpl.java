package creditcard.dao;


import creditcard.model.CreditCardAccount;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class AccountDAOImpl implements AccountDAO {
	Collection<CreditCardAccount> accountlist = new CopyOnWriteArrayList<CreditCardAccount>();

	public void saveAccount(CreditCardAccount creditCardAccount) {
		accountlist.add(creditCardAccount); // add the new
	}

	public void updateAccount(CreditCardAccount creditCardAccount) {
		CreditCardAccount accountexist = loadAccount(creditCardAccount.getAccountNumber());
		if (accountexist != null) {
			accountlist.remove(accountexist); // remove the old
			accountlist.add(creditCardAccount); // add the new
		}

	}

	public CreditCardAccount loadAccount(String accountNumber) {
		for (CreditCardAccount creditCardAccount : accountlist) {
			if (creditCardAccount.getAccountNumber() == accountNumber) {
				return creditCardAccount;
			}
		}
		return null;
	}

	public Collection<CreditCardAccount> getAccounts() {
		return accountlist;
	}

}
