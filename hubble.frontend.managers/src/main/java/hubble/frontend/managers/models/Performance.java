package hubble.frontend.managers.models;

import java.util.Date;

public class Performance {
    
    private String id;
    private BusinessApplication businessApplication;
    private Transaction transaction;
    private Location location;
    private Date timeStamp;
    private float responseTime;
    private String providerOrigin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BusinessApplication getBusinessApplication() {
        return businessApplication;
    }

    public void setBusinessApplication(BusinessApplication businessApplication) {
        this.businessApplication = businessApplication;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public float getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(float responseTime) {
        this.responseTime = responseTime;
    }

    public String getProviderOrigin() {
        return providerOrigin;
    }

    public void setProviderOrigin(String providerOrigin) {
        this.providerOrigin = providerOrigin;
    }
}
