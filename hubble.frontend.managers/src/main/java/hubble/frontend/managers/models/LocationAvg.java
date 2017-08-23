package hubble.frontend.managers.models;

import hubble.backend.core.enums.MonitoringFields;

public class LocationAvg {

    private Location location;
    private MonitoringFields.STATUS status;
    private int average;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public MonitoringFields.STATUS getStatus() {
        return status;
    }

    public void setStatus(MonitoringFields.STATUS status) {
        this.status = status;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }
}
