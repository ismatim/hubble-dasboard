package hubble.backend.business.services.models;

import java.util.List;

/**
 * Application entity is used as based of what an Application measured should
 be.
 *
 * @author Ismael J. Tisminetzky
 */
public class Application {

    private String applicationId;
    private String applicationName;
    private boolean active;
    private String timeZoneId;
    private Double okThreshold;
    private Double criticalThreshold;
    private Double outlierThreshold;
    private Double availabilityThreshold;
    private List<Location> locations;
    private List<Transaction> transactions;

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

    public Double getOkThreshold() {
        return okThreshold;
    }

    public void setOkThreshold(Double okThreshold) {
        this.okThreshold = okThreshold;
    }

    public Double getCriticalThreshold() {
        return criticalThreshold;
    }

    public void setCriticalThreshold(Double criticalThreshold) {
        this.criticalThreshold = criticalThreshold;
    }

    public Double getOutlierThreshold() {
        return outlierThreshold;
    }

    public void setOutlierThreshold(Double outlierThreshold) {
        this.outlierThreshold = outlierThreshold;
    }

    public Double getAvailabilityThreshold() {
        return availabilityThreshold;
    }

    public void setAvailabilityThreshold(Double availabilityThreshold) {
        this.availabilityThreshold = availabilityThreshold;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
