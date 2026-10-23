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


    @Override
    public void balance(Long accountNumber) {
        try{
            int balance = fetchAccount(accountNumber).getBalance();
            System.out.println("Account no: "+accountNumber+"\nBalance: $"+balance);

        }catch (NullPointerException e){
            System.out.println("Unable To fetch account : Error 89xhvdjb");
//            return -1;
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

            if( fetchAccount(transactionDTO.getAccountNumber()).getBalance() == balance+transactionDTO.getAmount()){
                response.add(true);
                response.add(balance);
                response.add(fetchAccount(transactionDTO.getAccountNumber()).getBalance());
                transactionDTO.setStatus(TransactionStatusTypes.Completed);
            }else{
                response.add(false);
                response.add("Technical problem while Deposit : Error 89xhvdjb");
                transactionDTO.setStatus(TransactionStatusTypes.DepositErr);

            }
            tools.display_message(response);
            return response;

        }catch (NullPointerException e){
            response.add(false);
            response.add("Unable To fetch account : Error 89xhvdjb");
            transactionDTO.setStatus(TransactionStatusTypes.DepositErr);
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
            if(balance<transactionDTO.getAmount()){
                response.add(false);
                response.add(balance);
                transactionDTO.setStatus(TransactionStatusTypes.InsufBal);
                System.out.println("Failed : Your account has less Balance. !!!!");
                return response;
            }
            fetchAccount(transactionDTO.getAccountNumber()).setBalance(balance-transactionDTO.getAmount());
            if( fetchAccount(transactionDTO.getAccountNumber()).getBalance() == balance-transactionDTO.getAmount()){
                response.add(true);
                response.add(balance);
                response.add(fetchAccount(transactionDTO.getAccountNumber()).getBalance());
                transactionDTO.setStatus(TransactionStatusTypes.Completed);
            }else{
                response.add(false);
                response.add("Technical problem while Deposit : Error 89xhvdjb");
                transactionDTO.setStatus(TransactionStatusTypes.WithdrawErr);

            }
            tools.display_message(response);
            return response;

        }catch (NullPointerException e){
            response.add(false);
            response.add("Unable To fetch account : Error 89xhvdjb");
            transactionDTO.setStatus(TransactionStatusTypes.WithdrawErr);
            tools.display_message(response);
            return response;
        }

    }

    @Override
    public List<Object> transfer(TransactionDTO transactionDTO) {
        List<Object> response  = new ArrayList<>();
        response.add("Transfer");
        if(fetchAccount(transactionDTO.getAccountNumber()).getBalance()<transactionDTO.getAmount()){
            response.add(false);
            response.add("Your account have insufficient balance");
            transactionDTO.setStatus(TransactionStatusTypes.InsufBal);
            tools.display_message(response);
            return response;
        }

           try {
               int sourceInitialBalance =  fetchAccount(transactionDTO.getAccountNumber()).getBalance();
               fetchAccount(transactionDTO.getAccountNumber()).setBalance(sourceInitialBalance-transactionDTO.getAmount());
               if(fetchAccount(transactionDTO.getDestinAccountNumber()).getBalance() == sourceInitialBalance-transactionDTO.getAmount()){
                   response.add(true);
                   transactionDTO.setStatus(TransactionStatusTypes.DebitDone);
                   System.out.println("Amount Debited to: "+transactionDTO.getDestinAccountNumber());
               }else{
                   fetchAccount(transactionDTO.getDestinAccountNumber()).setBalance(sourceInitialBalance);
                   transactionDTO.setStatus(TransactionStatusTypes.DebitErr);
                   response.add(false);
               }
//               response.add(true);
//               System.out.println("Amount credited from your account: "+transactionDTO.getAccountNumber());
           }catch (Exception e){
               response.add(false);
               return response;
           }
/*
    Destination Bank account money deposit process
    fetches the bank account balance and then perform operation on it and checks if the value is updated.
 */
        try {
            int destinInitialBalance = fetchAccount(transactionDTO.getDestinAccountNumber()).getBalance();
            fetchAccount(transactionDTO.getDestinAccountNumber()).setBalance(destinInitialBalance+transactionDTO.getAmount());
            if(fetchAccount(transactionDTO.getDestinAccountNumber()).getBalance() == destinInitialBalance+transactionDTO.getAmount()){
                response.add(true);
                transactionDTO.setStatus(TransactionStatusTypes.CreditDone);
                System.out.println("Amount Credited to: "+transactionDTO.getDestinAccountNumber());
            }else{
                fetchAccount(transactionDTO.getDestinAccountNumber()).setBalance(destinInitialBalance);
                transactionDTO.setStatus(TransactionStatusTypes.CreditErr);
                response.add(false);
            }

        }catch (Exception e){
            response.add(false);
            transactionDTO.setStatus(TransactionStatusTypes.CreditErr);
            return response;
        }

        if((boolean) response.get(1) && (boolean) response.get(2)){
            response.add(1,true);
            transactionDTO.setStatus(TransactionStatusTypes.Completed);
        }else{
            response.add(1,false);
            transactionDTO.setStatus(TransactionStatusTypes.Cancelled);
        }

        return response;
    }


}

class Tools{
    public void display_message(List<Object> response) {

        if (Objects.equals((String) response.getFirst(), "Transfer")) {
            for (Object o : response) {
                System.out.println(o);
            }
        } else {

            if ((boolean) response.get(1)) {
                System.out.println(response.get(0) + ": Transaction Successful ");
                System.out.println("Initial: $" + response.get(2) + " after: $" + response.get(3));
            } else {
                System.out.println(response.get(0) + ": Transaction Failed !! ");
                System.out.println(response.get(2));

            }
        }
    }
}
