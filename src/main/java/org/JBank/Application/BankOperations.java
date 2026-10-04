package org.JBank.Application;

public class BankOperations implements BankFunctions{


    @Override
    public int balance(long accNumber) {
        return 1;
    }

    @Override
    public int deposit(long accNumber, int amount) {
        return 1;
    }

    @Override
    public boolean withdraw(long accNumber, int amount) {
        return true;
    }

    @Override
    public boolean transfer(long accNumber, long destinAccNumber, int amount) {
        return true;
    }


}
