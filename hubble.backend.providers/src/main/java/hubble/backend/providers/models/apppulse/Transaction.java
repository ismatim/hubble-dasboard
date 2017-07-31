package hubble.backend.providers.models.apppulse;

public class Transaction {

    private String transactionId;
    private String transactionName;
    private String transactionType;
    private String scriptName;
    private int performanceOkThreshold;
    private int performanceCriticalThreshold;
    private boolean assignedToLocation;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public int getPerformanceOkThreshold() {
        return performanceOkThreshold;
    }

    public void setPerformanceOkThreshold(int performanceOkThreshold) {
        this.performanceOkThreshold = performanceOkThreshold;
    }

    public int getPerformanceCriticalThreshold() {
        return performanceCriticalThreshold;
    }

    public void setPerformanceCriticalThreshold(int performanceCriticalThreshold) {
        this.performanceCriticalThreshold = performanceCriticalThreshold;
    }

    public boolean isAssignedToLocation() {
        return assignedToLocation;
    }

    public void setAssignedToLocation(boolean assignedToLocation) {
        this.assignedToLocation = assignedToLocation;
    }
}
