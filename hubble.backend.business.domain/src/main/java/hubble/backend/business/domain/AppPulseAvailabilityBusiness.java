package hubble.backend.business.domain;

import java.util.List;

public class AppPulseAvailabilityBusiness {

    private int sampleId;
    private int timestamp;
    private String providerType;
    private String providerName;
    private int businessApplicationId;
    private String businessApplication;
    private String businessProcessId;
    private String businessProcess;
    private int transactionId;
    private String transaction;
    private int locationId;
    private String locationName;
    private String availabilityStatus;
    private int availabilityThreshold;
    private List<AppPulseErrorBusiness> errors;

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
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
    public String getBusinessProcessId() {
        return businessProcessId;
    }

    public void setBusinessProcessId(String businessProcessId) {
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

   public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

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

    public List<AppPulseErrorBusiness> getErrors() {
        return errors;
    }

    public void setErrors(List<AppPulseErrorBusiness> errors) {
        this.errors = errors;
    }
}
