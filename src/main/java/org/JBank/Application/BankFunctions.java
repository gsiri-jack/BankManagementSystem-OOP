package org.JBank.Application;

public interface BankFunctions {

    public int balance(long accNumber);
    public int deposit(long accNumber, int amount);
    public boolean withdraw(long accNumber, int amount);
    public boolean transfer(long accNumber, long destinAccNumber, int amount);

}
