package org.JBank.Application;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bank {

    //actions of Bank
    Map<Integer, String> actionsMenu =Map.of(
            0, "balance" ,
            1, "deposit",
            2, "withdraw",
            3, "transfer"
    );

    private long grandTotal;
    private List<BankAccount> bankAccountList = new ArrayList<>();


    public void increaseGTotal(int amount){
        grandTotal+=amount;
    }
    public void decreaseGTotal(int amount){
        grandTotal-=amount;
    }
    private final BankFunctions bankFunctions = new BankOperations();

    public long getGrandTotal() {
        return grandTotal;
    }

    public boolean performTransaction(TransactionDTO transactionDTO){

        switch (transactionDTO.getTransactionActionId()) {
            case 1:
                bankFunctions.deposit(
                        transactionDTO.getBankAccount().getAccNumber(),
                        transactionDTO.getAmount()
                );
            case 2:
                bankFunctions.withdraw(
                        transactionDTO.getBankAccount().getAccNumber(),
                        transactionDTO.getAmount()
                );
            case 3:
                bankFunctions.transfer(
                        transactionDTO.getBankAccount().getAccNumber(),
                        transactionDTO.getDestinBankAccount().getAccNumber(),
                        transactionDTO.getAmount()
                );
            case 0:
                bankFunctions.balance(
                        transactionDTO.getBankAccount().getAccNumber()
                );
        }
        return true;
    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void setBankAccountList(List<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }
}


