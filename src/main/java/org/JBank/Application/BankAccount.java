package org.JBank.Application;



public class BankAccount {

    private Bank bank;
    private  long accNumber;
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



    public boolean deposit_amount(int amount){
        this.amount+=amount;
        return true;
    }

    public boolean withdraw_amount(int amount){
        if(amount<0) {
            return false;
        }else {
            this.amount-=amount;
            return true;
        }
    }

    public Bank getBank() {
        return bank;
    }
}


