package framework.dao;


import framework.database.Accounts;
import framework.model.Account;
import framework.model.CreditCardAccount;

import java.util.List;
import java.util.stream.Collectors;

public class CreditAccountDAO implements DAO<Account, String> {

	public void add(Account creditCardAccount) {
		Accounts.accounts.add(creditCardAccount); // add the new
	}

	@Override
	public void delete(String id) {
		Account c = getById(id);
		Accounts.accounts.remove(c);
	}

	public boolean update(Account creditCardAccount) {
		Account accountexist = getById(creditCardAccount.getAccountNumber());
		if (accountexist != null && accountexist instanceof CreditCardAccount) {
			Accounts.accounts.remove(accountexist); // remove the old
			Accounts.accounts.add(creditCardAccount); // add the new
		}
		return true;
	}

	public Account getById(String accountNumber) {
		for (Account account : Accounts.accounts) {
			if (account.getAccountNumber() == accountNumber && account instanceof CreditCardAccount) {
				return account;
			}
		}
		return null;
	}

	public List<Account> getList() {
		return Accounts.accounts.stream()
				.filter(account -> account instanceof CreditCardAccount)
				.collect(Collectors.toList());
	}

}
