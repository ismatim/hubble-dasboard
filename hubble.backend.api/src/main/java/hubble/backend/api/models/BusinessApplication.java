package hubble.backend.api.models;

import java.util.List;

public class BusinessApplication extends ApiResponseBase {

    private String id;
    private String name;
    private String displayName;
    private boolean active;
    private String timeZoneId;
    private String timeZone;
    private int applicationConfigurationVersion;
    private int frequencyMin;
    private double defaultPerformanceOkThreshold;
    private double defaultPerformanceCriticalThreshold;
    private double defaultPerformanceOutlierThreshold;
    private double availabilityThreshold;
    private boolean dynamicThresholdEnabled;
    private List<String> transactionIds;
    private List<String> locationIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public int getApplicationConfigurationVersion() {
        return applicationConfigurationVersion;
    }

    public void setApplicationConfigurationVersion(int applicationConfigurationVersion) {
        this.applicationConfigurationVersion = applicationConfigurationVersion;
    }

    public int getFrequencyMin() {
        return frequencyMin;
    }

    public void setFrequencyMin(int frequencyMin) {
        this.frequencyMin = frequencyMin;
    }

    public double getDefaultPerformanceOkThreshold() {
        return defaultPerformanceOkThreshold;
    }

    public void setDefaultPerformanceOkThreshold(double defaultPerformanceOkThreshold) {
        this.defaultPerformanceOkThreshold = defaultPerformanceOkThreshold;
    }

    public double getDefaultPerformanceCriticalThreshold() {
        return defaultPerformanceCriticalThreshold;
    }

    public void setDefaultPerformanceCriticalThreshold(double defaultPerformanceCriticalThreshold) {
        this.defaultPerformanceCriticalThreshold = defaultPerformanceCriticalThreshold;
    }

    public double getDefaultPerformanceOutlierThreshold() {
        return defaultPerformanceOutlierThreshold;
    }

    public void setDefaultPerformanceOutlierThreshold(double defaultPerformanceOutlierThreshold) {
        this.defaultPerformanceOutlierThreshold = defaultPerformanceOutlierThreshold;
    }

    public double getAvailabilityThreshold() {
        return availabilityThreshold;
    }

    public void setAvailabilityThreshold(double availabilityThreshold) {
        this.availabilityThreshold = availabilityThreshold;
    }

    public boolean isDynamicThresholdEnabled() {
        return dynamicThresholdEnabled;
    }

    public void setDynamicThresholdEnabled(boolean dynamicThresholdEnabled) {
        this.dynamicThresholdEnabled = dynamicThresholdEnabled;
    }

    public List<String> getTransactionIds() {
        return transactionIds;
    }

    public void setTransactionIds(List<String> transactionIds) {
        this.transactionIds = transactionIds;
    }

    public List<String> getLocationIds() {
        return locationIds;
    }

    public void setLocationIds(List<String> locationIds) {
        this.locationIds = locationIds;
    }
}
