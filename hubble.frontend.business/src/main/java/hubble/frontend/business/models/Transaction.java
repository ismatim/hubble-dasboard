package hubble.frontend.business.models;

public class Transaction {

    private String id;
    private String parentApplicationId;
    private String name;
    private String displayName;
    private String type;
    private String scriptName;
    private int performanceOkThreshold;
    private int performanceCriticalThreshold;
    private boolean assignedToLocation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentApplicationId() {
        return parentApplicationId;
    }

    public void setParentApplicationId(String parentApplicationId) {
        this.parentApplicationId = parentApplicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
