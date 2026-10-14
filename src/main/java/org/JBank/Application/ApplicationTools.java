package org.JBank.Application;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ApplicationTools {
    //actions of Bank

    Map<Integer, String> userActionsMenu =Map.of(
            0, "balance" ,
            1, "deposit",
            2, "withdraw",
            3, "transfer"
    );

    private final Scanner sc = new Scanner(System.in);

    private final BankFunctions bankFunctions = new BankOperations();
    private final Bank bank;
    public ApplicationTools(Bank bank) {
        this.bank=bank;
    }


    public void StartApplication() {
        int userOption;
        while(true){
            showBankMenuUI();
            System.out.print("Enter Your option : ");

            userOption = sc.nextInt();
            sc.nextLine();

            if(userOption == 9){
                System.out.println("Exiting application... Goodbye!");
                return;
            }

            Application(userOption);
            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e){
                System.out.println("exception happened while sleep : "+e);
            }

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
            if((bank.checkBankAccountExist(accNumber))){

                if(userOption<3){
                    if(userOption==0){
                        System.out.println(bankFunctions.balance(bank.getBankAccountData(accNumber)));
                    }else{
                        System.out.println();
                        System.out.print("Please Enter amount : ");
                        int amount = sc.nextInt();
                        bank.startTransaction(userOption, accNumber, amount);
                    }
                } else if (userOption==3) {
                    System.out.println();
                    System.out.print("Please Enter amount : ");
                    int amount = sc.nextInt();
                    System.out.println();
                    System.out.print("Please Enter Receivers Account Number : ");
                    Long destinAccount = sc.nextLong();
                    bank.startInterAccountTransaction(accNumber, destinAccount, amount, userOption);

                }
            }else {
                System.out.println("* ! Please Check You have entered ! *");
            }
        }else {
            System.out.println("Please select correct Option");
        }
    }
}
