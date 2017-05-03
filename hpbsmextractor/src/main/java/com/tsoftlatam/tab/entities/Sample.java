package com.tsoftlatam.tab.entities;

import java.util.Date;

/**
 * Created by david.malagueno on 26/4/2017.
 */
public class Sample {
    private String applicationName;
    private String transactionName;
    private String locationName;
    private int errorCount;
    private float availabilityStatus;
    private float transactionStatus;//u_iStatus
    private double responseTime;
    private double timestamp;
    private Date fecha;

    public Date getFecha() {return fecha;}

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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public float getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(int availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public float getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(int transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }


    //Constructor
    public Sample(String appName, String tranName, String locName, int error, Float tranStatus, Float avStatus, double respTime, Long timestamp){
        this.applicationName = appName;
        this.transactionName = tranName;
        this.locationName = locName;
        this.transactionStatus = tranStatus;
        this.availabilityStatus = avStatus;
        this.errorCount = error;
        this.responseTime = respTime;
        this.timestamp = timestamp;
        this.fecha = setFecha(timestamp);
    }

    public Date setFecha(Long timestamp){
        return new Date(Double.valueOf(timestamp).longValue()*1000);
    }
}
