package org.JBank.Application;

import java.util.*;

public class BankOperations implements BankFunctions{

    private final Tools tools = new Tools();
    private final Bank bank;

    public BankOperations(Bank bank) {
        this.bank = bank;
    }

    private BankAccount fetchAccount(Long accountNumber){

            for(BankAccount acc : bank.getBankAccountList()) {
                if (acc.getAccNumber() == accountNumber) {
                    return acc;
                }
            }
        throw new RuntimeException("Account not found");
    }

//    @Override
//    public int balance(BankAccount bankAccount) {
//        return bankAccount.getBalance();
//    }
//
//    @Override
//    public List<Object> deposit(BankAccount bankAccount, int amount) {
//        List<Object> res = bankAccount.deposit_amount(amount);
//        tools.display_message(res);
//        return res;
//
//    }
//
//    @Override
//    public Map<Boolean, String> withdraw(BankAccount bankAccount, int amount) {
//
//        Map<Boolean, String> res= new HashMap<>();
//        boolean status = bankAccount.withdraw_amount(amount);
//
//        try {
//
//            if(status){
//                res.put(true, "Successful");
//            }else{
//                res.put(false, "Failed");
//            }
//        } catch (Exception e) {
//            System.out.println(e+"some error while try block of withdraw");
//        }
//
//        return res;
//    }
//
//    @Override
//    public Map<Boolean, String> transfer(
//            BankAccount bankAccount,
//            BankAccount destinBankAccount,
//            int amount
//    ) {
//        return new HashMap<>();
//    }
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
    public List<Object> deposit(TransactionDTO transactionDTO) {
        List<Object> response = new ArrayList<>();
        response.add("deposit");
        try{

            fetchAccount(transactionDTO.getAccountNumber());
            int balance = fetchAccount(transactionDTO.getAccountNumber()).getBalance();
            fetchAccount(transactionDTO.getAccountNumber()).setBalance(balance+transactionDTO.getAmount());
            response.add(true);
            response.add(balance);
            response.add(fetchAccount(transactionDTO.getAccountNumber()).getBalance());
            tools.display_message(response);
            return response;

        }catch (NullPointerException e){
            response.add(false);
            response.add("Unable To fetch account : Error 89xhvdjb");
            tools.display_message(response);
            return response;

        }

    }

    @Override
    public List<Object> withdraw(TransactionDTO transactionDTO) {
        List<Object> response = new ArrayList<>();
        response.add("Withdraw");
        try{

            int balance = Objects.requireNonNull(fetchAccount(transactionDTO.getAccountNumber())).getBalance();
            balance = balance-transactionDTO.getAmount();
            response.add(true);
            response.add(balance);
            response.add(fetchAccount(transactionDTO.getAccountNumber()).getBalance());
            return response;

        }catch (NullPointerException e){
            System.out.println("Unable To fetch account : Error 89xhvdjb");
            return null;
        }

    }

    @Override
    public List<Object> transfer(TransactionDTO transactionDTO) {
        return null;
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
