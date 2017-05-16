package com.tsoftlatam.tab.frontend.bles.hpbacble.models;

import javax.persistence.*;

@Entity(name = "hpbac_thresholds")
public class HPBacThreshold {

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

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getLocationId() {
        return locationId;
    }

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
