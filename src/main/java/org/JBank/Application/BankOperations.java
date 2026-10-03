package org.JBank.Application;

public class BankOperations implements BankFunctions{


    @Override
    public int balance() {
        return 1;
    }

    @Override
    public boolean withdraw(int amount) {
        return true;
    }

    @Override
    public boolean transfer() {
        return true;
    }

    @Override
    public int deposit() {
        return 1;
    }
}
