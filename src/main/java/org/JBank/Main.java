package org.JBank;

import org.JBank.Application.Bank;
import org.JBank.Application.BankAccount;
import org.JBank.Application.TransactionDTO;
import org.JBank.Simulation.HelperMethods;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();


        HelperMethods helperMethods = new HelperMethods();
        List<BankAccount> bankAccounts =  helperMethods.accountGenerator(5, bank);
        for(BankAccount acc : bankAccounts){
            System.out.println(acc.getAccNumber()+" "+ acc.getAmount());
        }
        bank.setBankAccountList(bankAccounts);
        bank.getBankAccountData(190005);


        bank.StartApplication();


//        List<TransactionDTO> transactionDTOS = helperMethods.generateTransaction(2, bankAccounts);




        }
    }

