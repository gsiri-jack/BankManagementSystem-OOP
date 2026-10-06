package org.JBank.Application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.security.SecureRandom;

public class TransactionServiceImpl implements TransactionService{
    private Bank bank ;
    public TransactionServiceImpl(Bank bank){
        this.bank=bank;
    }

    Map<Integer, String> action = new HashMap<>();

    private final SecureRandom random = new SecureRandom();
    {
        action.put(0, "Balance");
        action.put(1, "Deposit");
        action.put(2, "Withdraw");
        action.put(3, "Transfer");
    }
    @Override
    public TransactionDTO selfTransaction(long accNumber, int amount, int actionId) {
        System.out.println("selfTrasactions");
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String randomNum = Integer.toString(random.nextInt(200));
        String transactionId = "tid"+timestamp+Long.toString(accNumber)+"J"+randomNum;
        System.out.println("trasactionId: "+transactionId);
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(transactionId);
        transactionDTO.setAmount(amount);
        transactionDTO.setTransactionActionId(actionId);
        transactionDTO.setAccountNumber(accNumber);
        String op;
        try {
            op = action.get(actionId);
            transactionDTO.setTransactionAction(op);
            System.out.println("action: "+op);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        System.out.println("trasactionDetails: "+transactionDTO.getAccountNumber()+"-"+transactionDTO.getTransactionAction());
        bank.performTransaction(transactionDTO);
        transactionDTO.setTransactionAction(op);

        return transactionDTO;
    }

    @Override
    public TransactionDTO interAccountTransaction(long accNumber, long destinAccNumber, int amount, int actionId) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String randomNum = Integer.toString(random.nextInt(200));
        String transactionId = "tid"+timestamp+Long.toString(accNumber)+"t"+Long.toString(destinAccNumber)+"J"+randomNum;

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(transactionId);
        transactionDTO.setAccountNumber(accNumber);
        transactionDTO.setAmount(amount);
        transactionDTO.setDestinAccountNumber(destinAccNumber);
        transactionDTO.setTransactionActionId(actionId);
        transactionDTO.setTransactionAction(action.get(actionId));

        return transactionDTO;

    }
}
