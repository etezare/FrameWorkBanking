package creditcard.service;

import creditcard.model.CreditCardAccount;
import creditcard.model.CustomerCredit;
import framework.model.Account;
import framework.service.AccountService;

import java.util.List;

public interface CreditAccountService extends AccountService {

    Account createAccount(String accountNumber, String type, String customerName, String email
            , String customertype, String city, String state, String street, long zipcode);

    void minimumPaid();

    List<String> billingReport();
}
