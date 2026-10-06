package org.JBank.Application;

import java.util.List;
import java.util.Map;

public interface BankFunctions {

    public int balance(BankAccount bankAccount);
    public List<Object> deposit(BankAccount bankAccount, int amount);
    public Map<Boolean, String> withdraw(BankAccount bankAccount, int amount);
    public Map<Boolean, String> transfer(BankAccount bankAccount, BankAccount destinBankAccount, int amount);

}
