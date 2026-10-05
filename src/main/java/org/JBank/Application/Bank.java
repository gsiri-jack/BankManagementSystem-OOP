package org.JBank.Application;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Bank {

    //actions of Bank
    Map<Integer, String> actionsMenu =Map.of(
            0, "balance" ,
            1, "deposit",
            2, "withdraw",
            3, "transfer"
    );

    private long grandTotal;
    private static List<BankAccount> bankAccountList = new ArrayList<>();
    private final BankFunctions bankFunctions = new BankOperations();
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

    public Object performTransaction(TransactionDTO transactionDTO){
        BankAccount bankAccount = getBankAccountData(transactionDTO.getAccountNumber());
//        System.out.println(bankAccount.getAccNumber()+"account nuym");

        return switch (transactionDTO.getTransactionActionId()) {
            case 1 -> bankFunctions.deposit(
                    bankAccount,
                    transactionDTO.getAmount()
            );
            case 2 ->  bankFunctions.withdraw(

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
//        System.out.println("bank accountlist setup: "+bankAccountList.size());
        this.bankAccountList = bankAccountList;
//        System.out.println("bank accountlist setup after: "+this.bankAccountList.size());

    }

    public BankAccount getBankAccountData(long accountNumber){
        int flag=0;
//        System.out.println("bank details fetcher");
//        System.out.println(bankAccountList.size()+"size");
        for(BankAccount acc : bankAccountList){
//            System.out.println("gettimg bank details: "+acc.getAccNumber()+" "+accountNumber);
            if(acc.getAccNumber()==accountNumber){
                flag=1;
//                System.out.println("bank account found"+ acc.getAccNumber());
                return acc;
            }
        }
        return null;

    }


    public void showBankMenuUI(){

        System.out.println("\n Welcome To JBank \n".toUpperCase()+"-+-".repeat(10)+"\n Please select You option to continue forward: \n");


        System.out.println("|------------------|");
        for (int i=0; i<actionsMenu.size(); i++){
            System.out.print("|  "+i+".");
            System.out.printf("%-8s",actionsMenu.get(i));
            System.out.printf("%8s","|\n");
        }
        System.out.println("|------------------|");

    }

    public boolean checkBankAccountExist(Long accNumber){
        try{
            return getBankAccountData(accNumber) != null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void StartApplication(){
        showBankMenuUI();
        System.out.print("Enter Your option : ");
        int userOption = sc.nextInt();
        System.out.println();
        System.out.print("Please Enter Your Account Number : ");
        Long accNumber = sc.nextLong();
        if(checkBankAccountExist(accNumber)){
            System.out.println();
            System.out.print("Please Enter Your Account Number : ");
            int amount = sc.nextInt();
            startTransaction(userOption, accNumber, amount);
        }else {
            System.out.println("* ! Please Check You have entered ! *");
        }

    }

    private void startTransaction(int userOption, Long accNumber, int amount) {
        if(userOption==1 || userOption==2){

        }
    }
}


