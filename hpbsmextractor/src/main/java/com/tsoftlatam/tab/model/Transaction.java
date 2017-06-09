package com.tsoftlatam.tab.model;

public class Transaction {

    private String transactionName;

    public Transaction(String transactionName) {
        this.transactionName = transactionName;
    }

    public Transaction() {
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }
}
