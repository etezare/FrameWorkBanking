package creditcard;

import framework.model.Account;
import framework.model.AccountEntry;
import framework.model.Address;
import framework.model.CreditCardAccount;
import framework.model.Customer;
import framework.model.CustomerCredit;
import framework.service.CreditAccountService;
import framework.service.CreditAccountServiceImpl;

import java.util.List;

public class Application {
	public static void main(String[] args) {
		CreditAccountService accountService = new CreditAccountServiceImpl();

		Address address=new Address("7514geren","Iowa",12343,"fairfiled");
		CustomerCredit customer=new CustomerCredit("Essey","etezare@miu.edu",address,"company");
		CustomerCredit customer2=new CustomerCredit("Dawit","dhailu@miu.edu",address,"personal");
		CustomerCredit customer3=new CustomerCredit("Brhane","bteklehaimanot@miu.edu",address,"personal");
		// create 2 accounts;
		accountService.createAccount("1263862", customer,"bronze");
		accountService.createAccount("4253892", customer2,"gold");
		accountService.createAccount("12345",customer3,"Silver");
		// use account 1;

		accountService.deposit("1263862", 240);
		accountService.deposit("12345", 6000);
		accountService.deposit("1263862", 529);
		accountService.withdraw("1263862", 230);
		// use account 2;
		accountService.deposit("4253892", 12450);
		accountService.withdraw("4253892",500);
//		accountService.transferFunds("4253892", "1263862", 100, "payment of invoice 10232");
		// show balances
		List<String> reports=accountService.billingReport();
		accountService.addInterest();
		List<Account> accountlist = accountService.getAllAccounts();
		Customer customer4=null;
		for (Account accountFromDB : accountlist) {
			CreditCardAccount account = (CreditCardAccount) accountFromDB;
			customer4 = account.getCustomer();
			System.out.println("Statement for Account: " + account.getAccountNumber());
			System.out.println("Account Holder: " + customer.getName());

			System.out.println("-Date-------------------------"
					+ "-Description------------------"
					+ "-Amount-------------"+
					"---------Account Type---------");

			for (AccountEntry entry : account.getEntryList()) {
				System.out.printf("%30s%30s%20.2f%20s\n",
						entry.getDate().toString(),
						entry.getDescription(),
						entry.getAmount(),account.getAccountType());
			}

			System.out.println("----------------------------------------" + "----------------------------------------");
			System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
		}
	}

}
