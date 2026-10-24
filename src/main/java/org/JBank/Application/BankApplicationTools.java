package org.JBank.Application;

public interface BankApplicationTools {


    boolean adminVerify(Long adminPass);
    void openAdminApplication();
    void startInterAccountTransaction(Long accNumber, Long destinAccount, int amount, int userOption);
    void startTransaction(int userOption, Long accNumber, int amount);
    boolean checkBankAccountExist(Long accNumber);
    void balanceTransaction(int userOption, Long accNumber);

}
