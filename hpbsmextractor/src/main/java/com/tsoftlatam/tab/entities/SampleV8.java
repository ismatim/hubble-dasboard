package com.tsoftlatam.tab.entities;

import java.util.Date;

/**
 * Created by david.malagueno on 26/4/2017.
 */
public class SampleV8 {
    private String applicationName;
    private String transactionName;
    private String locationName;
    private int errorCount;
    private String availabilityStatus; //fail, success, etc
    private float transactionStatus;//u_iStatus en V9
    private double responseTime;
    private double timestamp;
    //private Date fecha;

    //Constructor
    public SampleV8(String appName, String tranName, String locName, int error, String avStatus, Float tranStatus, double respTime, Long timestamp){
        this.applicationName = appName;
        this.transactionName = tranName;
        this.locationName = locName;
        this.errorCount = error;
        this.availabilityStatus = avStatus;
        this.transactionStatus = tranStatus;
        this.responseTime = respTime;
        this.timestamp = timestamp;
        //this.fecha = setFecha(timestamp);
    }

    public Date setFecha(Long timestamp){
        return new Date(Double.valueOf(timestamp).longValue()*1000);
    }

    //public Date getFecha() {return fecha;}

    public String getApplicationName() {
        return applicationName;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public String getLocationName() {
        return locationName;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public float getTransactionStatus() {
        return transactionStatus;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public double getTimestamp() {
        return timestamp;
    }


    @Override
    public String toString() {
        return applicationName
                + ", " +transactionName
                + ", " +locationName
                + ", " +errorCount
                + ", " +availabilityStatus
                + ", " +transactionStatus
                + ", " +responseTime
                + ", " +timestamp;
                //+ ", " +fecha;
    }
}
