package framework.dao.test;

import framework.dao.AccountDAO;
import framework.dao.CustomerDAO;
import framework.dao.DAO;
import framework.model.Account;
import framework.model.AccountEntry;
import framework.model.Customer;
import framework.service.template.AccountMonthlyBillingReport;
import framework.service.template.MonthlyBillingReport;
import framework.util.Utils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class App {
	public static void main(String[] args) {
		
		//Test data
		DAO data = new CustomerDAO();
		List<Customer> customers = data.getList();
		System.out.println((Arrays.toString(customers.toArray())));

		data = new AccountDAO();
		List<Account> accounts = data.getList();
		System.out.println((Arrays.toString(accounts.toArray())));
		accounts.forEach(x ->{
			System.out.println(String.format("Account %s %s", x.getCustomer(), x.getAccountNumber()));
			Collection<AccountEntry> ae = x.getEntryList();
			ae.forEach(y -> {
				System.out.println(String.format("**** Entry: %s %s %s",
						y.getDescription(),
						Utils.convertDateToString(y.getDate()),
						String.valueOf(y.getAmount())
				));
			}
			);

			System.out.println("Monthly Report for account " + x.getAccountNumber());
			MonthlyBillingReport billingReport = new AccountMonthlyBillingReport(x, LocalDate.now());
			System.out.println("----------Monthly Bill Report----------------");
			billingReport.showReport();
			System.out.println("----------End of Monthly Bill Report----------------");
			System.out.println("----------------------------");
		});


	}
}
