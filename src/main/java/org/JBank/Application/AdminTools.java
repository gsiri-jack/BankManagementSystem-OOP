package org.JBank.Application;

import org.JBank.Simulation.HelperMethods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdminTools {
    private final long password;

    private final List<TransactionDTO> transactionsList;
    private final List<BankAccount> bankAccountList;
    private final Bank bank;

    HelperMethods helperMethods = new HelperMethods();

    Scanner sc = new Scanner(System.in);



    private final Map<Integer, String> adminActionsMenu = Map.of(
            91, "show Transactions" ,
            92, "perform Tests transactions",
            90, "exit"
    );


    public AdminTools(int i, List<TransactionDTO> transactionsList, List<BankAccount> bankAccountList, Bank bank) {
        this.password=i;
        this.transactionsList=transactionsList;
        this.bankAccountList=bankAccountList;
        this.bank=bank;
    }


    public boolean verify(Long adminPass) {
        return this.password == adminPass;
    }


    public void showTransactionsTable(){
        for(TransactionDTO transaction : transactionsList){
            displayTransaction(transaction);
        }
    }

    private void displayTransaction(TransactionDTO transaction) {
        System.out.print("| " + transaction.getAccountNumber()+ " |");
//        System.out.printf("%30s","|");
        System.out.printf("| %-42s |" , transaction.getTransactionId());
        System.out.print("| " + transaction.getTransactionActionId()+ " |");
        System.out.printf("| %-8s |" , transaction.getTransactionAction());
//        System.out.printf("%8s","|");
        if(transaction.getTransactionActionId()==3){
            System.out.print("| " + transaction.getDestinAccountNumber()+ " |");
        }else {
            System.out.print("| *NONE* |");
        }
        System.out.print("| " + transaction.getAmount());
        System.out.printf("%5s","|");
        System.out.printf("| %-9s |" , transaction.getStatus());
        System.out.println();

    }

    public void startAdminApplication() {

        boolean loopKey = true;
        while(loopKey){
            showAdminMenu();
            System.out.println("Admin Enter your option: ");
            int adminOption = sc.nextInt();
            sc.nextLine();

            switch (adminOption) {
                case 91 -> this.showTransactionsTable();
                case 92 -> this.performTestTransaction();
                case 90 -> {
                    loopKey=false;
                }
            }
        }
    }

    public  void showAdminMenu(){
        System.out.println("Welcome!! Admin");
        for (int val : adminActionsMenu.keySet()){
            System.out.print("|  "+val+".");
            System.out.printf("%-8s",adminActionsMenu.get(val));
            System.out.printf("%8s","|\n");
        }

    }

    public void performTestTransaction(){
        System.out.println("Enter Size of tests: ");
        int num = sc.nextInt();
        System.out.println(bankAccountList.size()+"Size in  adminTools");
        helperMethods.generateTransaction(num, bankAccountList);

        System.out.println("Generated Transactions");
    }

    public void generateTestBankAccounts(int size, Bank bank){

    }

}
