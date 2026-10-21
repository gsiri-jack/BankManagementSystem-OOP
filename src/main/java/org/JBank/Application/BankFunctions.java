package org.JBank.Application;

import java.util.List;

public interface BankFunctions {

    void balance(Long accountNumber);
    List<Object> deposit(TransactionDTO transactionDTO);
    List<Object> withdraw(TransactionDTO transactionDTO);
    List<Object> transfer(TransactionDTO transactionDTO);


}
