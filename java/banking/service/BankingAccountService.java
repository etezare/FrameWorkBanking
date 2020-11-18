package banking.service;


import framework.model.Account;
import framework.service.AccountService;

public interface BankingAccountService extends AccountService {


    Account createAccount(String accountNumber, String customerId, String type, String customerType);
    

}
