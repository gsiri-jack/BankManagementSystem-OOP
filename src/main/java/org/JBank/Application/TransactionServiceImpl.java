package org.JBank.Application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.security.SecureRandom;

public class TransactionServiceImpl implements TransactionService{

    Map<Integer, String> action = new HashMap<>();
    private final SecureRandom random = new SecureRandom();
    {

        action.put(1, "Deposit");
        action.put(2, "Withdraw");
        action.put(3, "Transfer");
    }
    @Override
    public TransactionDTO selfTransaction(long accNumber, int amount, int actionId) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String randomNum = Integer.toString(random.nextInt(200));
        String transactionId = "tid"+timestamp+Long.toString(accNumber)+"J"+randomNum;
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(transactionId);
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
