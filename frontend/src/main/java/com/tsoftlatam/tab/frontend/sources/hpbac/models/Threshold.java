package com.tsoftlatam.tab.frontend.sources.hpbac.models;

import javax.persistence.*;

@Entity(name = "hpbac_thresholds")
public class Threshold {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "application_id")
    private int applicationId;

    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "location_id")
    private int locationId;

    @Column(name = "ok_threshold")
    private float okThreshold;

    @Column(name = "critical_threshold")
    private float criticalThreshold;

    @Column(name = "outlier_threshold")
    private float outlierThreshold;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApplicationId() {
        return applicationId;
    }

    @ManyToOne(targetEntity = Application.class)
    @JoinColumn(name = "application_id")
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    @ManyToOne(targetEntity = Transaction.class)
    @JoinColumn(name = "transaction_id")
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getLocationId() {
        return locationId;
    }

    @ManyToOne(targetEntity = Location.class)
    @JoinColumn(name = "location_id")
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public float getOkThreshold() {
        return okThreshold;
    }

    public void setOkThreshold(float okThreshold) {
        this.okThreshold = okThreshold;
    }

    public float getCriticalThreshold() {
        return criticalThreshold;
    }

    public void setCriticalThreshold(float criticalThreshold) {
        this.criticalThreshold = criticalThreshold;
    }

    public float getOutlierThreshold() {
        return outlierThreshold;
    }

    public void setOutlierThreshold(float outlierThreshold) {
        this.outlierThreshold = outlierThreshold;
    }
}
