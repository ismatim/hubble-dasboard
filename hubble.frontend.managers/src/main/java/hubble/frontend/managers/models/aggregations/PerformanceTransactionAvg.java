package hubble.frontend.managers.models.aggregations;

import hubble.backend.core.enums.MonitoringFields;

public class PerformanceTransactionAvg {

    private String applicationId;
    private String applicationName;
    private boolean active;
    private String timeZoneId;
    private int okThreshold;
    private int criticalThreshold;
    private int outlierThreshold;
    private int availabilityThreshold;
    private MonitoringFields.STATUS Status;
    private float average;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public int getOkThreshold() {
        return okThreshold;
    }

    public void setOkThreshold(int okThreshold) {
        this.okThreshold = okThreshold;
    }

    public int getCriticalThreshold() {
        return criticalThreshold;
    }

    public void setCriticalThreshold(int criticalThreshold) {
        this.criticalThreshold = criticalThreshold;
    }

    public int getOutlierThreshold() {
        return outlierThreshold;
    }

    public void setOutlierThreshold(int outlierThreshold) {
        this.outlierThreshold = outlierThreshold;
    }

    public int getAvailabilityThreshold() {
        return availabilityThreshold;
    }

    public void setAvailabilityThreshold(int availabilityThreshold) {
        this.availabilityThreshold = availabilityThreshold;
    }

    public MonitoringFields.STATUS getStatus() {
        return Status;
    }

    public void setStatus(MonitoringFields.STATUS Status) {
        this.Status = Status;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }


}
