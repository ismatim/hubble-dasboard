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
    @Column(name = "display_name")
    private String displayName;

    @NotNull
    @Column(name = "profile_id")
    private int profileId;

    public Transaction(String transactionName, String displayName, int profileId) {
        this.transactionName = transactionName;
        this.displayName = displayName;
        this.profileId = profileId;
    }

    public  Transaction(){

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getProfileId() {
        return profileId;
    }

    //@ManyToOne(targetEntity = Profile.class)
    //@JoinColumn(name = "application_id")
    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

}
