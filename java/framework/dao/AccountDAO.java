package framework.dao;

import framework.database.Accounts;
import framework.model.Account;

import java.util.List;

public class AccountDAO implements DAO<Account, String> {

	@Override
	public List<Account> getList() {
		return Accounts.accounts;
	}

	@Override
	public Account getById(String id) {
		return Accounts.accounts.stream()
				.filter(account -> account.getAccountNumber().equals(id))
				.findFirst()
				.orElse(null);
	}

	@Override
	public void add(Account a) {
		Accounts.accounts.add(a);
	}

	@Override
	public void delete(String id) {
		Account c = getById(id);
		Accounts.accounts.remove(c);
	}

	@Override
	public boolean update(Account a) {
		Account entity = getById(a.getAccountNumber());
		if (entity != null) {
			Accounts.accounts.remove(entity); // remove the old
			Accounts.accounts.add(a); // add the new
		}
		return true;
	}
}
