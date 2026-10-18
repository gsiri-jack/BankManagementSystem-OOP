package org.JBank.Application;


import java.util.ArrayList;
import java.util.List;

public class Bank {



    private long grandTotal;
    private List<BankAccount> bankAccountList = new ArrayList<>();

    private final BankFunctions bankFunctions = new BankOperations(this);
    private final TransactionService transactionService = new TransactionServiceImpl(this);

    public ApplicationTools applicationTools= new ApplicationTools(this);



    public void increaseGTotal(int amount){
        grandTotal+=amount;
    }
    public void decreaseGTotal(int amount){
        grandTotal-=amount;
    }

    public long getGrandTotal() {
        return grandTotal;
    }

    public void performTransaction(TransactionDTO transactionDTO){
        BankAccount bankAccount = getBankAccountData(transactionDTO.getAccountNumber());

        System.out.println("Performing transaction : "+transactionDTO.getTransactionActionId());
        switch (transactionDTO.getTransactionActionId()) {
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
            default -> {
            }
        }

    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void setBankAccountList(List<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;

    }

    void startInterAccountTransaction(Long accNumber, Long destinAccount, int amount, int userOption) {
        transactionService.interAccountTransaction(accNumber,destinAccount, amount, userOption);
    }

    void startTransaction(int userOption, Long accNumber, int amount) {
        System.out.println("called out for Transaction");
        transactionService.selfTransaction(accNumber, amount, userOption);
    }

    public BankAccount getBankAccountData(long accountNumber){
        for(BankAccount acc : bankAccountList){
            if(acc.getAccNumber()==accountNumber){
                return acc;
            }
        }
        return null;
    }

    public boolean checkBankAccountExist(Long accNumber){
        try{
            return getBankAccountData(accNumber) != null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void startApplication(){
        applicationTools.StartApplication();
    }


}

