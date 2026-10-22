package org.JBank.Application;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        System.out.println("Performing transaction : "+transactionDTO.getTransactionActionId());
        switch (transactionDTO.getTransactionActionId()) {
            case 1 -> bankFunctions.deposit(transactionDTO);
            case 2 -> bankFunctions.withdraw(transactionDTO);
            case 3 -> bankFunctions.transfer(transactionDTO);
            default -> {
            }
        }

    }
    public void performTransaction(BalanceTransactionDto balanceTransactionDto){
        bankFunctions.balance(
                balanceTransactionDto.getAccountNumber()
        );
    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void setBankAccountList(List<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;

    }

    void startInterAccountTransaction(Long accNumber, Long destinAccount, int amount, int userOption) {
        if(Objects.equals(accNumber, destinAccount)){
            System.out.println("Cannot Transfer to your own account");
            return;
        }
        transactionService.interAccountTransaction(accNumber,destinAccount, amount, userOption);
    }

    void startTransaction(int userOption, Long accNumber, int amount) {
        System.out.println("called out for Transaction");
        transactionService.selfTransaction(accNumber, amount, userOption);
    }
    void balanceTransaction(int userOption, Long accNumber){
        transactionService.checkBalanceAction(accNumber);
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

