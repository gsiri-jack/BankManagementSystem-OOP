package org.JBank;

import org.JBank.Application.Bank;
import org.JBank.Application.BankAccount;
import org.JBank.Simulation.HelperMethods;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();

        HelperMethods helperMethods = new HelperMethods();
        List<BankAccount> bankAccounts =  helperMethods.accountGenerator(50, bank);

        }
    }

