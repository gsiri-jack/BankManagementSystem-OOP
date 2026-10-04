package org.JBank.Application;

public class TransactionDTO {

    private long accountNumber;
    private String transactionId;
    private int transactionActionId;
    private String transactionAction;
    private long destinAccountNumber;
    private int amount;

    public int getTransactionActionId() {
        return transactionActionId;
    }

    public void setTransactionActionId(int transactionActionId) {
        this.transactionActionId = transactionActionId;
    }

    public String getTransactionAction() {
        return transactionAction;
    }

    public void setTransactionAction(String transactionAction) {
        this.transactionAction = transactionAction;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getDestinAccountNumber() {
        return destinAccountNumber;
    }

    public void setDestinAccountNumber(long destinAccountNumber) {
        this.destinAccountNumber = destinAccountNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
