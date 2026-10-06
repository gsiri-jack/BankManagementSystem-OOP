package org.JBank.Application;



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



    public boolean deposit_amount(int amount){
        System.out.println("Depositing Amount "+this.amount);
        this.amount+=amount;
        System.out.println("after Depositing Amount "+this.amount);
        return true;
    }

    public boolean withdraw_amount(int amount){
        // have to implement the exception Handling for the amount check

        if(amount<0) {
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


