package org.JBank.Application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.security.SecureRandom;

public class TransactionServiceImpl implements TransactionService{

    Map<Integer, String> action = new HashMap<>();
    private SecureRandom random = new SecureRandom();
    {

        action.put(1, "Deposit");
        action.put(2, "Withdraw");
        action.put(3, "Transfer");
    }
    @Override
    public TransactionDTO selfTransaction(BankAccount bankAccount, int amount, int actionId) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String randomNum = Integer.toString(random.nextInt(200));
        String transactionId = "tid"+timestamp+Long.toString(bankAccount.getAccNumber())+"J"+randomNum;
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(transactionId);
        transactionDTO.setBankAccount(bankAccount);
        transactionDTO.setAmount(amount);

        transactionDTO.setTransactionActionId(actionId);
        String op;
        try {
            op = action.get(actionId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        transactionDTO.setTransactionAction(op);

        return transactionDTO;
    }

    @Override
    public TransactionDTO interAccountTransaction(BankAccount bankAccount, BankAccount destinBankAccount, int amount) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String randomNum = Integer.toString(random.nextInt(200));
        String transactionId = "tid"+timestamp+Long.toString(bankAccount.getAccNumber())+"t"+Long.toString(destinBankAccount.getAccNumber())+"J"+randomNum;

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(transactionId);
        transactionDTO.setBankAccount(bankAccount);
        transactionDTO.setAmount(amount);
        transactionDTO.setDestinBankAccount(destinBankAccount);
        transactionDTO.setTransactionActionId(3);
        transactionDTO.setTransactionAction(action.get(3));

        return transactionDTO;

    }
}
