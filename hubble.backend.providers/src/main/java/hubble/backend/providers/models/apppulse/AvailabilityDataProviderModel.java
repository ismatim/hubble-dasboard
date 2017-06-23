package hubble.backend.providers.models.apppulse;

import java.util.ArrayList;

public class AvailabilityDataProviderModel implements AvailabilityData {

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
    private ArrayList<ErrorProviderModel> errors;

    public AvailabilityDataProviderModel(){
        errors = new ArrayList<>();
    }

    @Override
    public String getApplicationName() {
        return applicationName;
    }

    @Override
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public String getTransactionName() {
        return transactionName;
    }

    @Override
    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    @Override
    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String getLocationName() {
        return locationName;
    }

    @Override
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String getLocationId() {
        return locationId;
    }

    @Override
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @Override
    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public long getResponseTime() {
        return responseTime;
    }

    @Override
    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public String getServerName() {
        return serverName;
    }

    @Override
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Override
    public String getScriptName() {
        return scriptName;
    }

    @Override
    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    @Override
    public String getPerformanceStatus() {
        return performanceStatus;
    }

    @Override
    public void setPerformanceStatus(String performanceStatus) {
        this.performanceStatus = performanceStatus;
    }

    @Override
    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    @Override
    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    @Override
    public int getPoorThreshold() {
        return poorThreshold;
    }

    @Override
    public void setPoorThreshold(int poorThreshold) {
        this.poorThreshold = poorThreshold;
    }

    @Override
    public int getCriticalThreshold() {
        return criticalThreshold;
    }

    @Override
    public void setCriticalThreshold(int criticalThreshold) {
        this.criticalThreshold = criticalThreshold;
    }

    @Override
    public int getAvailabilityFailIfAbove() {
        return availabilityFailIfAbove;
    }

    @Override
    public void setAvailabilityFailIfAbove(int availabilityFailIfAbove) {
        this.availabilityFailIfAbove = availabilityFailIfAbove;
    }

    @Override
    public int getNumberOfErrors() {
        return numberOfErrors;
    }


    @Override
    public void setNumberOfErrors(int numberOfErrors) {
        this.numberOfErrors = numberOfErrors;
    }

    @Override
    public ArrayList<ErrorProviderModel> getErrors() {
        return errors;
    }

    @Override
    public void setErrors(ArrayList<ErrorProviderModel> errors) {
        this.errors = errors;
    }

    @Override
    public String getApplicationId() {
        return applicationId;
    }

    @Override
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

}
