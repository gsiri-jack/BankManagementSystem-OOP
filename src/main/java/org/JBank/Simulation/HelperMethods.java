package org.JBank.Simulation;


import org.JBank.Application.*;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class HelperMethods {
    protected Random rand = new Random();
    public List<BankAccount> accountGenerator(int num, Bank bank){
        List<BankAccount> accounts = new ArrayList<>();
        for(int i=1;i<=num; i++){
            long acc_number = 190000+i;

            int amount = rand.nextInt(501)+1000;
            try{
                BankAccount bankAccount = new BankAccount(acc_number, amount, bank);
//                System.out.println(bankAccount.getAmount()+" - " +bankAccount.getAccNumber() + " - " + bankAccount.getBank().getGrandTotal());

                accounts.add(bankAccount);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return accounts;
    }

        public List<TransactionDTO> generateTransaction(int num, List<BankAccount> bankAccounts){
            List<TransactionDTO> transactionDTOS= new ArrayList<>();
            for (int i=0; i<num; i++){
                System.out.println(i);
                int action = rand.nextInt(3)+1;
                TransactionService transactionService = new TransactionServiceImpl();
                TransactionDTO transactionDTO = new TransactionDTO();
                if(action==1 || action == 2){
                  transactionDTO = transactionService.selfTransaction(getRandomBankAccount(bankAccounts), getRandomAmount(), action);
              } else {
                    transactionDTO = transactionService.interAccountTransaction(getTwoRandomBankAccount(bankAccounts).getFirst(), getTwoRandomBankAccount(bankAccounts).getLast(), getRandomAmount(), action);
                }
                transactionDTOS.add(transactionDTO);
            }
            return transactionDTOS;

        }

        public long getRandomBankAccount(List<BankAccount> bankAccounts){
            int index = rand.nextInt(bankAccounts.size());
            return bankAccounts.get(index).getAccNumber();
        }

        public List<Long> getTwoRandomBankAccount(List<BankAccount> bankAccounts){
            int num1 = ThreadLocalRandom.current().nextInt(0, bankAccounts.size());
            int num2 = ThreadLocalRandom.current().nextInt(0, bankAccounts.size());
            List<Long> responseBankAccounts = new ArrayList<>();
            responseBankAccounts.add(bankAccounts.get(num1).getAccNumber());
            responseBankAccounts.add(bankAccounts.get(num2).getAccNumber());
            return responseBankAccounts;
        }

        public int getRandomAmount(){
        return rand.nextInt(500);
        }

}
