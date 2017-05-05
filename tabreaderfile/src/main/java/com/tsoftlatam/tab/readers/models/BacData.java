package com.tsoftlatam.tab.readers.models;

import java.util.Date;

public class BacData {

    private String applicationName;
    private String transactionName;
    private String locationName;
    private String availabilityStatus; //fail, success, etc
    //private int errorCount;
    private String errorCount;
    //private float transactionStatus;//u_iStatus en V9
    private String transactionStatus;//u_iStatus en V9
    //private double responseTime;
    private String responseTime;
    //private double timestamp;
    private String timestamp;
    private Date fecha;

    public BacData(String appName, String tranName, String locName, String avStatus, String error,  String tranStatus, String respTime, String timestamp){
        this.applicationName = appName;
        this.transactionName = tranName;
        this.locationName = locName;
        this.errorCount = error;
        this.availabilityStatus = avStatus;
        this.transactionStatus = tranStatus;
        this.responseTime = respTime;
        this.timestamp = timestamp;
        this.fecha = setFecha(timestamp);
    }

    public Date setFecha(String timestamp){
        return new Date(Double.valueOf(timestamp).longValue()*1000);
    }

    public Date getFecha() {
        return fecha;
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(String errorCount) {
        this.errorCount = errorCount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
