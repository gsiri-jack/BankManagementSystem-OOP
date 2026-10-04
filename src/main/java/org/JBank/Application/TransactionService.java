package org.JBank.Application;

public interface TransactionService {

    public TransactionDTO selfTransaction(BankAccount bankAccount, int amount, int actionId);
    public TransactionDTO interAccountTransaction(BankAccount bankAccount, BankAccount destinBankAccount, int amount, int action);

}
