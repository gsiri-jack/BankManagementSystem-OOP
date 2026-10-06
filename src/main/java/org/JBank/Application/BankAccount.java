package org.JBank.Application;


import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private final Bank bank;
    private final long accNumber;
    private int amount;


    public BankAccount(long accNumber, Bank bank){
        this.accNumber=accNumber;
        this.amount=0;
        this.bank=bank;


    }

    public BankAccount(long accNumber, int amount, Bank bank){
        this.accNumber=accNumber;
        this.amount=amount;
        this.bank=bank;
        this.bank.increaseGTotal(amount);


    }

    public long getAccNumber(){
        return accNumber;
    }


    public int getAmount(){
        return amount;
    }



    public List<Object> deposit_amount(int amount){
        List<Object> response = new ArrayList<>();
        response.add(0, "Deposit");

        try {
            int initialAmount = this.amount;
            this.amount+=amount;
            int finalAmount = this.amount;
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
        // have to implement the exception Handling for the amount check
        List<Object> response = new ArrayList<>();
        response.add(0, "Deposit");
        if(this.amount<=0 || amount>this.amount) {
            return false;
        }else {
            this.amount-=amount;
            System.out.println("amount withdraw : "+amount);
            return true;
        }
    }

    public Bank getBank() {
        return bank;
    }
}


