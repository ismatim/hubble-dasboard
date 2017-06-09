package com.tsoftlatam.tab.readers.models;


public class Location {

    private String applicationName;
    private String transactionName;
    private String locationName;

    public Location(String applicationName, String transactionName, String locationName) {
        this.applicationName = applicationName;
        this.transactionName = transactionName;
        this.locationName = locationName;
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
}
