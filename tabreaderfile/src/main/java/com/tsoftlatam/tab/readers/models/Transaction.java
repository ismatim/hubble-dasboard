package com.tsoftlatam.tab.readers.models;


public class Transaction {

    private String applicationName;
    private String transactionName;

    public Transaction(String applicationName, String transactionName) {
        this.applicationName = applicationName;
        this.transactionName = transactionName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }
}
