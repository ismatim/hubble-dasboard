package hubble.backend.business.services.models;

import java.util.List;

public class ApplicationDto {

    private String applicationId;
    private String applicationName;
    private boolean active;
    private String timeZoneId;
    private Float okThreshold;
    private Float criticalThreshold;
    private Float outlierThreshold;
    private Float availabilityThreshold;
    private List<LocationDto> locations;
    private List<TransactionDto> transactions;

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

    public Float getOkThreshold() {
        return okThreshold;
    }

    public void setOkThreshold(Float okThreshold) {
        this.okThreshold = okThreshold;
    }

    public Float getCriticalThreshold() {
        return criticalThreshold;
    }

    public void setCriticalThreshold(Float criticalThreshold) {
        this.criticalThreshold = criticalThreshold;
    }

    public Float getOutlierThreshold() {
        return outlierThreshold;
    }

    public void setOutlierThreshold(Float outlierThreshold) {
        this.outlierThreshold = outlierThreshold;
    }

    public Float getAvailabilityThreshold() {
        return availabilityThreshold;
    }

    public void setAvailabilityThreshold(Float availabilityThreshold) {
        this.availabilityThreshold = availabilityThreshold;
    }

    public List<LocationDto> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDto> locations) {
        this.locations = locations;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }
}
