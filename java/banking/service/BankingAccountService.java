package banking.service;


import framework.model.Account;
import framework.service.AccountService;

import java.util.List;

public interface BankingAccountService extends AccountService {


    Account createAccount(String accountNumber, String customerId, String type, String customerType);

    List<String> billingReport();

    }
