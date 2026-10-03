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
        List<BankAccount> bankAccounts =  helperMethods.accountGenerator(50, bank);
        List<TransactionDTO> transactionDTOS = helperMethods.generateTransaction(10, bankAccounts);
        System.out.println(transactionDTOS.size());
        for (TransactionDTO i : transactionDTOS){
            System.out.println(
                   "trasaction ID : " +  i.getTransactionId()+ "\n" +
                           "trasaction name : " +  i.getTransactionAction()+ "\n" +
                           "trasaction acc : " +  i.getBankAccount().getAccNumber()+ "\n"

            );
        }

        }
    }

