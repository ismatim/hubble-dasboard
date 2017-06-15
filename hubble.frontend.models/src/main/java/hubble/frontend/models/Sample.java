package hubble.frontend.models;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sample {
    
    private String profileName;
    private String transactionName;
    private String transactionDisplayName;
    private String locationName;
    private String locationDisplayName;
    private String availabilityStatus; //fail, success, etc
    private String availabilityDisplayStatus;
    private String errorCount;
    private String transactionStatus;//u_iStatus en V9
    private String transactionDisplayStatus;
    private String responseTime;
    private String timestamp;
    private String fecha;

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(new Date(Double.valueOf(this.timestamp).longValue()*1000));
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(String errorCount) {
        this.errorCount = errorCount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getResponseTime() {

        return String.valueOf(Float.valueOf(responseTime)/1000);
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getProfileAndLocation(){
        return this.profileName+" - "+this.locationName;
    }

    public String getProfileAndTransaction(){
        return this.profileName+" - "+this.transactionName;
    }
}
