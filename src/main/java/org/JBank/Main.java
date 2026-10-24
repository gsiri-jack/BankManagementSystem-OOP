package org.JBank;

import org.JBank.Application.Bank;
import org.JBank.Application.BankAccount;
import org.JBank.Simulation.HelperMethods;

import java.util.List;

public class Main {
    static void main() {

        Bank bank = new Bank();


        for(BankAccount acc : bankAccounts){
            System.out.println(acc.getAccNumber()+" "+ acc.getBalance());
        }
        bank.setBankAccountList(bankAccounts);
        bank.getBankAccountData(190005);

        bank.startApplication();






        }
    }

