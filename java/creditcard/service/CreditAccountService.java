package creditcard.service;

import creditcard.model.CustomerCredit;
import framework.model.Account;
import framework.service.AccountService;

import java.util.List;

public interface CreditAccountService extends AccountService {

    Account createAccount(String accountNumber, CustomerCredit customer, String type);

    void minimumPaid();

    List<String> billingReport();
}
