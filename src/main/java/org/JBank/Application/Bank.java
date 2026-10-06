package org.JBank.Application;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Bank {

    //actions of Bank
    Map<Integer, String> userActionsMenu =Map.of(
            0, "balance" ,
            1, "deposit",
            2, "withdraw",
            3, "transfer"
    );

    private long grandTotal;
    private static List<BankAccount> bankAccountList = new ArrayList<>();
    private final BankFunctions bankFunctions = new BankOperations();
    private TransactionService transactionService = new TransactionServiceImpl(this);
    private Scanner sc = new Scanner(System.in);


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
        Bank.bankAccountList = bankAccountList;

    }

    private void startInterAccountTransaction(Long accNumber, Long destinAccount, int amount, int userOption) {
        transactionService.interAccountTransaction(accNumber,destinAccount, amount, userOption);
    }

    private void startTransaction(int userOption, Long accNumber, int amount) {
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

    public void StartApplication(){

        int userOption;
        while(true){
            showBankMenuUI();
            System.out.print("Enter Your option : ");
            userOption= sc.nextInt();
            if(userOption==9){
                return;
            }
            Application(userOption);
        }
    }

    public void showBankMenuUI(){

        System.out.println("\n Welcome To JBank \n".toUpperCase()+"-+-".repeat(10)+"\n Please select You option to continue forward: \n");


        System.out.println("|------------------|");
        for (int i=0; i<userActionsMenu.size(); i++){
            System.out.print("|  "+i+".");
            System.out.printf("%-8s",userActionsMenu.get(i));
            System.out.printf("%8s","|\n");
        }
        System.out.println("|------------------|");

    }

    public void Application(int userOption){

        if(userActionsMenu.containsKey(userOption)){
            System.out.println();
            System.out.print("Please Enter Your Account Number : ");
            Long accNumber = sc.nextLong();
            if(checkBankAccountExist(accNumber)){

                if(userOption<3){
                    if(userOption==0){
                        System.out.println(bankFunctions.balance(getBankAccountData(accNumber)));
                    }else{
                        System.out.println();
                        System.out.print("Please Enter amount : ");
                        int amount = sc.nextInt();
                        startTransaction(userOption, accNumber, amount);
                    }
                } else if (userOption==3) {
                    System.out.println();
                    System.out.print("Please Enter amount : ");
                    int amount = sc.nextInt();
                    System.out.println();
                    System.out.print("Please Enter Receivers Account Number : ");
                    Long destinAccount = sc.nextLong();
                    startInterAccountTransaction(accNumber, destinAccount, amount, userOption);

                }
            }else {
                System.out.println("* ! Please Check You have entered ! *");
            }
        }else {
            System.out.println("Please select correct Option");
        }
    }



}


