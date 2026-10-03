package org.JBank.Simulation;


import org.JBank.Application.Bank;
import org.JBank.Application.BankAccount;

import java.util.ArrayList;
import java.util.List;
import org.JBank.Application.BankAccount;
import java.util.Random;

public class HelperMethods {

    public List<BankAccount> accountGenerator(int num, Bank bank){
        List<BankAccount> accounts = new ArrayList<>();
        for(int i=1;i<=num; i++){
            long acc_number = 190000+i;
            Random rand = new Random();
            int amount = rand.nextInt(501)+1000;
            try{
                BankAccount bankAccount = new BankAccount(acc_number, amount, bank);
//                System.out.println(bankAccount.getAmount()+" - " +bankAccount.getAccNumber() + " - " + bankAccount.getBank().getGrandTotal());

                accounts.add(bankAccount);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return accounts;
    }

}
