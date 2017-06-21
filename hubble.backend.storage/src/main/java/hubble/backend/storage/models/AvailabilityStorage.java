package hubble.backend.storage.models;

import java.util.List;

public class AvailabilityStorage {

    private String applicationName;
    private String applicationId;
    private String transactionName;
    private String transactionId;
    private String locationName;
    private String locationId;
    private long timeStamp;
    private long responseTime;
    private String serverName;
    private String scriptName;
    private String performanceStatus;
    private String availabilityStatus;
    private int poorThreshold;
    private int criticalThreshold;
    private int availabilityFailIfAbove;
    private int numberOfErrors;
    private List<ErrorStorage> errors;

    public AvailabilityStorage(){

    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }


    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getPerformanceStatus() {
        return performanceStatus;
    }

    public void setPerformanceStatus(String performanceStatus) {
        this.performanceStatus = performanceStatus;
    }

    /**
     * @return the availabilityStatus
     */
    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    /**
     * @param availabilityStatus the availabilityStatus to set
     */
    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    /**
     * @return the poorThreshold
     */
    public int getPoorThreshold() {
        return poorThreshold;
    }

    /**
     * @param poorThreshold the poorThreshold to set
     */
    public void setPoorThreshold(int poorThreshold) {
        this.poorThreshold = poorThreshold;
    }

    /**
     * @return the criticalThreshold
     */
    public int getCriticalThreshold() {
        return criticalThreshold;
    }

    /**
     * @param criticalThreshold the criticalThreshold to set
     */
    public void setCriticalThreshold(int criticalThreshold) {
        this.criticalThreshold = criticalThreshold;
    }

    /**
     * @return the availabilityFailIfAbove
     */
    public int getAvailabilityFailIfAbove() {
        return availabilityFailIfAbove;
    }

    /**
     * @param availabilityFailIfAbove the availabilityFailIfAbove to set
     */
    public void setAvailabilityFailIfAbove(int availabilityFailIfAbove) {
        this.availabilityFailIfAbove = availabilityFailIfAbove;
    }

    /**
     * @return the numberOfErrors
     */
    public int getNumberOfErrors() {
        return numberOfErrors;
    }

    /**
     * @param numberOfErrors the numberOfErrors to set
     */
    public void setNumberOfErrors(int numberOfErrors) {
        this.numberOfErrors = numberOfErrors;
    }

    /**
     * @return the errors
     */
    public List<ErrorStorage> getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(List<ErrorStorage> errors) {
        this.errors = errors;
    }

}
