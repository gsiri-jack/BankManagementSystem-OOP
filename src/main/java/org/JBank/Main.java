package org.JBank;

import org.JBank.Application.Bank;
import org.JBank.Application.BankAccount;
import org.JBank.Simulation.HelperMethods;
import org.JBank.Simulation.Worker;

import java.util.List;

public class Main {
    static void main() {

        Bank bank = new Bank();
        HelperMethods helperMethods = new HelperMethods();

        helperMethods.accountGenerator(10, bank);

        Worker worker = new Worker(bank.getBankAccountList());

        worker.StartWork();


//        for(BankAccount acc : bankAccounts){
//            System.out.println(acc.getAccNumber()+" "+ acc.getBalance());
//        }
//        bank.setBankAccountList(bankAccounts);

        bank.startApplication();











        }
    }

