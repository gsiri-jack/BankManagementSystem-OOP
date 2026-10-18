package org.JBank.Application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BankOperations implements BankFunctions{

    private Tools tools = new Tools();
    private final Bank bank;

    public BankOperations(Bank bank) {
        this.bank = bank;
    }

    private BankAccount fetchAccount(Long accountNumber){
        for(BankAccount acc : bank.getBankAccountList()){
            if(acc.getAccNumber() == accountNumber){
                return acc;
            }
        }
        return null;
    }

    @Override
    public int balance(BankAccount bankAccount) {
        return bankAccount.getBalance();
    }

    @Override
    public List<Object> deposit(BankAccount bankAccount, int amount) {
        List<Object> res = bankAccount.deposit_amount(amount);
        tools.display_message(res);
        return res;

    }

    @Override
    public Map<Boolean, String> withdraw(BankAccount bankAccount, int amount) {

        Map<Boolean, String> res= new HashMap<>();
        boolean status = bankAccount.withdraw_amount(amount);

        try {

            if(status){
                res.put(true, "Successful");
            }else{
                res.put(false, "Failed");
            }
        } catch (Exception e) {
            System.out.println(e+"some error while try block of withdraw");
        }

        return res;
    }

    @Override
    public Map<Boolean, String> transfer(
            BankAccount bankAccount,
            BankAccount destinBankAccount,
            int amount
    ) {
        Map<Boolean, String> res= new HashMap<>();
        return res;
    }
// New update .

    @Override
    public int balance(Long accountNumber) {

        try{

            return Objects.requireNonNull(fetchAccount(accountNumber)).getBalance();

        }catch (NullPointerException e){
            System.out.println("Unable To fetch account : Error 89xhvdjb");
            return -1;
        }

    }

    @Override
    public List<Object> deposit(Long accountNumber, int amount) {
        try{

            int balance = Objects.requireNonNull(fetchAccount(accountNumber)).getBalance();
            balance = balance+amount;
            return

        }catch (NullPointerException e){
            System.out.println("Unable To fetch account : Error 89xhvdjb");
            return null;
        }
        return List.of();

    }

    @Override
    public Map<Boolean, String> withdraw(Long accountNumber, int amount) {
        return Map.of();
    }

    @Override
    public Map<Boolean, String> transfer(Long accountNumber, Long destinAccountNumber, int amount) {
        return Map.of();
    }


}

class Tools{
    public void display_message(List<Object> response){
        if((boolean) response.get(1)){
            System.out.println(response.get(0)+": Transaction Successful ");
            System.out.println("Initial: "+response.get(2)+" after: "+response.get(3));
        }else{
            System.out.println(response.get(0)+": Transaction Failed !! ");
            System.out.println(response.get(2));

        }
    }
}
