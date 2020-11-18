package creditcard.dao;


import creditcard.model.CreditCardAccount;
import framework.dao.AccountDAO;
import framework.database.Accounts;
import framework.model.Account;

import java.util.List;
import java.util.stream.Collectors;

public class CreditAccountDAO extends AccountDAO {

	@Override
	public List<Account> getList() {
		return Accounts.accounts.stream()
				.filter(account -> account instanceof CreditCardAccount)
				.collect(Collectors.toList());
	}

	@Override
	public Account getById(String id) {
		return Accounts.accounts.stream()
				.filter(account -> account.getAccountNumber().equals(id) && account instanceof CreditCardAccount)
				.findFirst()
				.orElse(null);
	}

	public boolean update(Account account) {
		Account entity = getById(account.getAccountNumber());
		if (entity != null && entity instanceof CreditCardAccount) {
			Accounts.accounts.remove(entity); // remove the old
			Accounts.accounts.add(account); // add the new
		}
		return true;
	}

}
