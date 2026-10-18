package org.JBank.Application;


import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private final Bank bank;
    private final long accNumber;
    private int balance;


    public BankAccount(long accNumber, Bank bank){
        this.accNumber=accNumber;
        this.balance =0;
        this.bank=bank;


    }

    public BankAccount(long accNumber, int amount, Bank bank){
        this.accNumber=accNumber;
        this.balance =amount;
        this.bank=bank;
        this.bank.increaseGTotal(amount);


    }

    public long getAccNumber(){
        return accNumber;
    }



    public int getBalance(){
        return balance;
    }



    public List<Object> deposit_amount(int amount){
        List<Object> response = new ArrayList<>();
        response.add(0, "Deposit");

        try {
            int initialAmount = this.balance;
            this.balance +=amount;
            int finalAmount = this.balance;
            response.add(1,true);
            response.add(2, initialAmount);
            response.add(3, finalAmount);
        } catch (Exception e) {
            response.add(1,false);
            response.add(2, e);
        }

        return response;
    }

    public boolean withdraw_amount(int amount){
        // have to implement the exception Handling for the balance check
        List<Object> response = new ArrayList<>();
        response.add(0, "Deposit");
        if(this.balance <=0 || amount>this.balance) {
            return false;
        }else {
            this.balance -=amount;
            System.out.println("balance withdraw : "+amount);
            return true;
        }
    }

    public Bank getBank() {
        return bank;
    }
}


