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
    private final BankFunctions bankFunctions = new BankOperations();


    public void increaseGTotal(int amount){
        grandTotal+=amount;
    }
    public void decreaseGTotal(int amount){
        grandTotal-=amount;
    }

    public long getGrandTotal() {
        return grandTotal;
    }

    public Object performTransaction(TransactionDTO transactionDTO){
        BankAccount bankAccount = getBankAccountData(transactionDTO.getAccountNumber());
        return switch (transactionDTO.getTransactionActionId()) {
            case 1 -> bankFunctions.deposit(
                    bankAccount,
                    transactionDTO.getAmount()
            );
            case 2 -> bankFunctions.withdraw(
                    bankAccount,
                    transactionDTO.getAmount()
            );
            case 3 -> bankFunctions.transfer(
                    bankAccount,
                   getBankAccountData(transactionDTO.getDestinAccountNumber()),
                    transactionDTO.getAmount()
            );
            case 0 -> bankFunctions.balance(
                    bankAccount
            );
            default -> null;
        };

    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void setBankAccountList(List<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }

    public BankAccount getBankAccountData(long accountNumber){
        int flag=0;
        for(BankAccount acc : bankAccountList){
            if(acc.getAccNumber()==accountNumber){
                flag=1;
                return acc;
            }
        }
        return null;

    }
}


