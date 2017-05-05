package com.tsoftlatam.tab.frontend.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BacSample {

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
    private String fecha;

    public BacSample(String appName, String tranName, String locName, String avStatus, String error,  String tranStatus, String respTime, String timestamp){
        this.applicationName = appName;
        this.transactionName = tranName;
        this.locationName = locName;
        this.errorCount = error;
        this.availabilityStatus = avStatus;
        this.transactionStatus = tranStatus;
        this.responseTime = respTime;
        this.timestamp = timestamp;
        setFecha(timestamp);
    }



    public void setFecha(String timestamp){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        this.fecha = dateFormat.format(new Date(Double.valueOf(timestamp).longValue()*1000));
        //this.fecha = new Date(Double.valueOf(timestamp).longValue()*1000);
    }

    public String getFecha() {
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
