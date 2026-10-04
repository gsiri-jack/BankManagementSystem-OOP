package org.JBank.Application;

public interface TransactionService {

    public TransactionDTO selfTransaction(long accNumber, int amount, int actionId);
    public TransactionDTO interAccountTransaction(long accNumber, long destinAccNumber, int amount, int actionId);

}
