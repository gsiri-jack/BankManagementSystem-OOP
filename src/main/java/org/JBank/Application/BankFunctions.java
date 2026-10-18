package org.JBank.Application;

import java.util.List;
import java.util.Map;

public interface BankFunctions {

    int balance(BankAccount bankAccount);
    List<Object> deposit(BankAccount bankAccount, int amount);
    Map<Boolean, String> withdraw(BankAccount bankAccount, int amount);
    Map<Boolean, String> transfer(BankAccount bankAccount, BankAccount destinBankAccount, int amount);

    int balance(Long accountNumber);
    List<Object> deposit(Long accountNumber, int amount);
    Map<Boolean, String> withdraw(Long accountNumber, int amount);
    Map<Boolean, String> transfer(Long accountNumber, Long destinAccountNumber, int amount);


}
