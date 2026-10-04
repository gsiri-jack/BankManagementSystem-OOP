package org.JBank.Application;

import java.util.HashMap;
import java.util.Map;

public class BankOperations implements BankFunctions{


    @Override
    public int balance(BankAccount bankAccount) {
        return bankAccount.getAmount();
    }

    @Override
    public Map<Boolean, String> deposit(BankAccount bankAccount, int amount) {

        Map<Boolean, String> res= new HashMap<>();
        try {
            boolean status = bankAccount.deposit_amount(amount);
            if(status){
                res.put(true, "Successful");
            }else{
                res.put(false, "Failed");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;

    }

    @Override
    public Map<Boolean, String> withdraw(BankAccount bankAccount, int amount) {
        Map<Boolean, String> res= new HashMap<>();
        try {
            boolean status = bankAccount.deposit_amount(amount);
            if(status){
                res.put(true, "Successful");
            }else{
                res.put(false, "Failed");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public Map<Boolean, String> transfer(BankAccount bankAccount, BankAccount destinBankAccount, int amount) {
        Map<Boolean, String> res= new HashMap<>();
        return res;
    }


}
