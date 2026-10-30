package org.JBank.Simulation;

import org.JBank.Application.BankAccount;
import org.JBank.Simulation.HelperMethods;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker implements Runnable{

    private final List<BankAccount> bankAccounts;

    ExecutorService service = Executors.newFixedThreadPool(2);

    public void StartWork(){
        for(int i=0; i<10; i++){
            service.execute(this);
        }
    }

    public Worker (List<BankAccount> bankAccounts){
        this.bankAccounts=bankAccounts;
        System.out.println(bankAccounts.size()+ "- Bank account Size in the Worker.");
    }


    @Override
    public void run() {
        HelperMethods helperMethods = new HelperMethods();
        helperMethods.generateOneTransaction(bankAccounts);
    }
}