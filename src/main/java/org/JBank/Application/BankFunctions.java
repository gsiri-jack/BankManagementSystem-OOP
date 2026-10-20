package org.JBank.Application;

import java.util.List;
import java.util.Map;

public interface BankFunctions {

    int balance(Long accountNumber);
    List<Object> deposit(TransactionDTO transactionDTO);
    List<Object> withdraw(TransactionDTO transactionDTO);
    List<Object> transfer(TransactionDTO transactionDTO);


}
