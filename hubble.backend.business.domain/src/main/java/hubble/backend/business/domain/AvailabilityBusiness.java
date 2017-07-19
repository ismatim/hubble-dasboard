package hubble.backend.business.domain;

import java.util.Date;
import java.util.List;

public class AvailabilityBusiness {

    private int sampleId;
    private Date timestamp;
    private String providerOrigin;
    private String providerName;
    private int businessApplicationId;
    private String businessApplication;
    private int businessProcessId;
    private String businessProcess;
    private int transactionId;
    private String transaction;
    private int locationId;
    private String locationName;
    private String availabilityStatus;
    private int availabilityThreshold;
    private List<ErrorBusiness> errors;

    public AvailabilityBusiness() {
    }

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getProviderOrigin() {
        return providerOrigin;
    }

    public void setProviderOrigin(String providerOrigin) {
        this.providerOrigin = providerOrigin;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public int getBusinessApplicationId() {
        return businessApplicationId;
    }

    public void setBusinessApplicationId(int businessApplicationId) {
        this.businessApplicationId = businessApplicationId;
    }

    public String getBusinessApplication() {
        return businessApplication;
    }

    public void setBusinessApplication(String businessApplication) {
        this.businessApplication = businessApplication;
    }

    /**
     * @return the businessProcess
     */
    public int getBusinessProcessId() {
        return businessProcessId;
    }

    public void setBusinessProcessId(int businessProcessId) {
        this.businessProcessId = businessProcessId;
    }

    public String getBusinessProcess() {
        return businessProcess;
    }

    public void setBusinessProcess(String businessProcess) {
        this.businessProcess = businessProcess;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public int getLoctionId() {
        return locationId;
    }

    public void setLoctionId(int loctionId) {
        this.locationId = loctionId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    /**
     * @return the availabilityStatus
     */
    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public int getAvailabilityThreshold() {
        return availabilityThreshold;
    }

    public void setAvailabilityThreshold(int availabilityThreshold) {
        this.availabilityThreshold = availabilityThreshold;
    }
    
    public List<ErrorBusiness> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorBusiness> errors) {
        this.errors = errors;
    }
}
