//package creditcard.commandpattern;
//
//import creditcard.service.AccountService;
//
//public class WithdrawCommand implements Command {
//    private AccountService accountService;
//    private double amount;
//    private String accountNumber;
//    WithdrawCommand(String accountNumber, double amount, AccountService accountService){
//        this.accountNumber=accountNumber;
//        this.amount=amount;
//        this.accountService=accountService;
//    }
//    @Override
//    public void excute() {
//        accountService.withdraw(accountNumber,amount);
//    }
//
//    @Override
//    public void unExcute() {
//        accountService.deposit(accountNumber,amount);
//    }
//}
