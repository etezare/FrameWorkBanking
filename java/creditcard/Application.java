package creditcard;

import creditcard.model.*;
import creditcard.service.AccountService;
import creditcard.service.AccountServiceImpl;

import java.util.Collection;

public class Application {
	public static void main(String[] args) {
		AccountService accountService = new AccountServiceImpl();

		Address address=new Address("7514geren","Iowa",12343,"fairfiled");
		Customer customer=new CompanyCreditAccount("Essey","etezare@miu.edu",address);
		Customer customer2=new PersonalCreditAccount("Dawit","dhailu@miu.edu",address);
		Customer customer3=new PersonalCreditAccount("Brhane","bteklehaimanot@miu.edu",address);
		// create 2 accounts;
		accountService.createAccount("1263862", customer,"bronze");
		accountService.createAccount("4253892", customer2,"gold");
		accountService.createAccount("12345",customer3,"Silver");
		// use account 1;
		accountService.billingReport("1263862");
		accountService.deposit("1263862", 240);
		accountService.deposit("12345", 6000);
		accountService.deposit("1263862", 529);
		accountService.withdraw("1263862", 230);
		// use account 2;
		accountService.deposit("4253892", 12450);
//		accountService.transferFunds("4253892", "1263862", 100, "payment of invoice 10232");
		// show balances
		accountService.addInterest();
		Collection<CreditCardAccount> accountlist = accountService.getAllAccounts();
		Customer customer4=null;
		for (CreditCardAccount account : accountlist) {
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
						entry.getAmount(),account.getType());
			}

			System.out.println("----------------------------------------" + "----------------------------------------");
			System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
		}
	}

}
