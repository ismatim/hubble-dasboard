package com.tsoftlatam.tab.model;

public class Location {

    private String LocationName;

    public Location(String locationName) {
        LocationName = locationName;
    }

    public Location() {
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }
}
