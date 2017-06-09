package com.tsoftlatam.tab.model;

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
    private double transactionStatus;//u_iStatus en V9
    private double responseTime;
    private double timestamp;
    private int id;
    //private Date fecha;

    //Constructor
    public SampleV8(String appName, String tranName, String locName, int error, String avStatus, double tranStatus, double respTime, double timestamp, int id){
        this.applicationName = appName;
        this.transactionName = tranName;
        this.locationName = locName;
        this.errorCount = error;
        this.availabilityStatus = avStatus;
        this.transactionStatus = tranStatus;
        this.responseTime = respTime;
        this.timestamp = timestamp;
        this.id= id;
        //this.fecha = setFecha(timestamp);
    }

    public Date setFecha(Long timestamp){
        return new Date(Double.valueOf(timestamp).longValue()*1000);
    }

    //public Date getFecha() {return fecha;}

    public int getId() {
        return id;
    }

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

    public double getTransactionStatus() {
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
        return "{" +
                "applicationName='" + applicationName + '\'' +
                ", transactionName='" + transactionName + '\'' +
                ", locationName='" + locationName + '\'' +
                ", errorCount=" + errorCount +
                ", availabilityStatus='" + availabilityStatus + '\'' +
                ", transactionStatus=" + transactionStatus +
                ", responseTime=" + responseTime +
                ", timestamp=" + timestamp +
                ", id=" + id +
                '}';
    }
}
