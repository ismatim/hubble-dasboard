package hubble.backend.providers.models.apppulse;

import java.util.List;

public class ApplicationData {

    private String applicationId;
    private String applicationName;
    private boolean applicationIsActive;
    private String timeZoneId;
    private String timeZone;
    private int applicationConfigurationVersion;
    private int frequencyInMin;
    private int defaultPerformanceOkThreshold;
    private int defaultPerformanceCriticalThreshold;
    private String statusCriticalIfLessThan;
    private int transactionFailThreshold;
    private boolean dynamicThresholdEnabled;
    private String dynamicThresholdUpperLimit;

    private List<Transaction> transactions;
    private List<Location> locations;

    public ApplicationData() {
    }

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

    public boolean getApplicationIsActive() {
        return applicationIsActive;
    }

    public void setApplicationIsActive(boolean active) {
        this.applicationIsActive = active;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public int getApplicationConfigurationVersion() {
        return applicationConfigurationVersion;
    }

    public void setApplicationConfigurationVersion(int applicationConfigurationVersion) {
        this.applicationConfigurationVersion = applicationConfigurationVersion;
    }

    public int getFrequencyInMin() {
        return frequencyInMin;
    }

    public void setFrequencyInMin(int frequencyInMin) {
        this.frequencyInMin = frequencyInMin;
    }

    public int getDefaultPerformanceOkThreshold() {
        return defaultPerformanceOkThreshold;
    }

    public void setDefaultPerformanceOkThreshold(int defaultPerformanceOkThreshold) {
        this.defaultPerformanceOkThreshold = defaultPerformanceOkThreshold;
    }

    public int getDefaultPerformanceCriticalThreshold() {
        return defaultPerformanceCriticalThreshold;
    }

    public void setDefaultPerformanceCriticalThreshold(int defaultPerformanceCriticalThreshold) {
        this.defaultPerformanceCriticalThreshold = defaultPerformanceCriticalThreshold;
    }

    public String getStatusCriticalIfLessThan() {
        return statusCriticalIfLessThan;
    }

    public void setStatusCriticalIfLessThan(String statusCriticalIfLessThan) {
        this.statusCriticalIfLessThan = statusCriticalIfLessThan;
    }

    public int getTransactionFailThreshold() {
        return transactionFailThreshold;
    }

    public void setTransactionFailThreshold(int transactionFailThreshold) {
        this.transactionFailThreshold = transactionFailThreshold;
    }

    public boolean isDynamicThresholdEnabled() {
        return dynamicThresholdEnabled;
    }

    public void setDynamicThresholdEnabled(boolean dynamicThresholdEnabled) {
        this.dynamicThresholdEnabled = dynamicThresholdEnabled;
    }

    public String getDynamicThresholdUpperLimit() {
        return dynamicThresholdUpperLimit;
    }

    public void setDynamicThresholdUpperLimit(String dynamicThresholdUpperLimit) {
        this.dynamicThresholdUpperLimit = dynamicThresholdUpperLimit;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
