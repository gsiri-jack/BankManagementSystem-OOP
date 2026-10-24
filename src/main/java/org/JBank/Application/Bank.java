package org.JBank.Application;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bank implements BankApplicationTools{



    private long grandTotal;
    private List<BankAccount> bankAccountList = new ArrayList<>();
    private List<TransactionDTO> transactionsList = new ArrayList<>();

    private final BankFunctions bankFunctions = new BankOperations(this);
    private final TransactionService transactionService = new TransactionServiceImpl(this);
    {
        System.out.println(bankAccountList.size()+"Size in  Bank");

    }
    private final AdminTools adminTools = new AdminTools(96528, this.transactionsList, this.bankAccountList, this);
    public ApplicationTools applicationTools= new ApplicationTools(this, adminTools);



    public void increaseGTotal(int amount){
        grandTotal+=amount;
    }
    public void decreaseGTotal(int amount){
        grandTotal-=amount;
    }

    public List<TransactionDTO> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<TransactionDTO> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public void addTransactionToList(TransactionDTO transactionDTO){
        transactionsList.add(transactionDTO);
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

    public void startInterAccountTransaction(Long accNumber, Long destinAccount, int amount, int userOption) {
        if(Objects.equals(accNumber, destinAccount)){
            System.out.println("Cannot Transfer to your own account");
            return;
        }
        transactionService.interAccountTransaction(accNumber,destinAccount, amount, userOption);
    }

    public void startTransaction(int userOption, Long accNumber, int amount) {
        System.out.println("called out for Transaction");
        transactionService.selfTransaction(accNumber, amount, userOption);
    }
public void balanceTransaction(int userOption, Long accNumber){
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


    public boolean adminVerify(Long adminPass) {
        return adminTools.verify(adminPass);
    }

    public void openAdminApplication() {
        adminTools.startAdminApplication();
    }
}

