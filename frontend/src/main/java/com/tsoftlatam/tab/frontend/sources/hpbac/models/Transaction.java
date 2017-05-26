package com.tsoftlatam.tab.frontend.sources.hpbac.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "hpbac_transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(name = "transaction_name")
    private String transactionName;

    @NotNull
    @Column(name = "transaction_display_name")
    private String transactionDisplayName;

    @NotNull
    @Column(name = "application_id")
    private int applicationId;

    public Transaction(String transactionName, String transactionDisplayName, int applicationId) {
        this.transactionName = transactionName;
        this.transactionDisplayName = transactionDisplayName;
        this.applicationId = applicationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionDisplayName() {
        return transactionDisplayName;
    }

    public void setTransactionDisplayName(String transactionDisplayName) {
        this.transactionDisplayName = transactionDisplayName;
    }

    public int getApplicationId() {
        return applicationId;
    }

    //@ManyToOne(targetEntity = Application.class)
    //@JoinColumn(name = "application_id")
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }
}
