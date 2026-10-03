package org.JBank.Application;

public class TransactionDTO {

    private BankAccount bankAccount;
    private String transactionId;
    private int transactionActionId;
    private String transactionAction;
    private BankAccount destinBankAccount;
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

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getDestinBankAccount() {
        return destinBankAccount;
    }

    public void setDestinBankAccount(BankAccount destinBankAccount) {
        this.destinBankAccount = destinBankAccount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
