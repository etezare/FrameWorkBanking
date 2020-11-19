package framework.dao;

import creditcard.model.CreditCardAccount;
import framework.database.Accounts;
import framework.model.Account;

import java.util.List;
import java.util.stream.Collectors;

public class AccountDAO implements DAO<Account, String> {

	@Override
	public List<Account> getList() {

		return Accounts.data
				.stream()
				.filter(x -> !(x instanceof CreditCardAccount) )
				.collect(Collectors.toList());
	}

	@Override
	public Account getById(String id) {
		return Accounts.data.stream()
				.filter(x -> !(x instanceof CreditCardAccount) )
				.filter(account -> account.getAccountNumber().equals(id))
				.findFirst()
				.orElse(null);
	}

	@Override
	public void add(Account a) {
		Accounts.data.add(a);
	}

	@Override
	public void delete(String id) {
		Account c = getById(id);
		Accounts.data.remove(c);
	}

	@Override
	public boolean update(Account a) {
		Account entity = getById(a.getAccountNumber());
		if (entity != null && !Accounts.data
				.stream()
				.filter(x -> !(x instanceof CreditCardAccount) )
				.collect(Collectors.toList())
				.contains(a)
		) {
//			Accounts.accounts.remove(entity); // remove the old
			Accounts.data.add(a); // add the new
		}
		return true;
	}
}
