package hubble.backend.providers.models.apppulse;

import java.util.ArrayList;


public interface AvailabilityData {

    String getApplicationId();

    String getApplicationName();

    int getAvailabilityFailIfAbove();

    String getAvailabilityStatus();

    int getCriticalThreshold();

    ArrayList<ErrorProviderModel> getErrors();

    String getLocationId();

    String getLocationName();

    int getNumberOfErrors();

    String getPerformanceStatus();

    int getPoorThreshold();

    long getResponseTime();

    String getScriptName();

    String getServerName();

    long getTimeStamp();

    String getTransactionId();

    String getTransactionName();

    void setApplicationId(String applicationId);

    void setApplicationName(String applicationName);

    void setAvailabilityFailIfAbove(int availabilityFailIfAbove);

    void setAvailabilityStatus(String availabilityStatus);

    void setCriticalThreshold(int criticalThreshold);

    void setErrors(ArrayList<ErrorProviderModel> errors);

    void setLocationId(String locationId);

    void setLocationName(String locationName);

    void setNumberOfErrors(int numberOfErrors);

    void setPerformanceStatus(String performanceStatus);

    void setPoorThreshold(int poorThreshold);

    void setResponseTime(long responseTime);

    void setScriptName(String scriptName);

    void setServerName(String serverName);

    void setTimeStamp(long timeStamp);

    void setTransactionId(String transactionId);

    void setTransactionName(String transactionName);
}
