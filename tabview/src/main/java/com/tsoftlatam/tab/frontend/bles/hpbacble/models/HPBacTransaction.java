package com.tsoftlatam.tab.frontend.bles.hpbacble.models;

import javax.persistence.*;

@Entity(name = "hpbac_transactions")
public class HPBacTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "transaction_name")
    private String transactionName;

    @Column(name = "transaction_display_name")
    private String transactionDisplayName;

    //@ManyToOne(targetEntity = HPBacApplication.class)
    @Column(name = "application_id")
    private int applicationId;

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

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }
}
