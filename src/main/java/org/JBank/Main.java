package org.JBank;

import org.JBank.Application.Bank;
import org.JBank.Application.BankAccount;
import org.JBank.Application.TransactionDTO;
import org.JBank.Simulation.HelperMethods;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();
        bank.showBankMenuUI();

//        HelperMethods helperMethods = new HelperMethods();
//        List<BankAccount> bankAccounts =  helperMethods.accountGenerator(5, bank);
//        bank.setBankAccountList(bankAccounts);
//        List<TransactionDTO> transactionDTOS = helperMethods.generateTransaction(2, bankAccounts);




        }
    }

