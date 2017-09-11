package hubble.backend.providers.models.alm;

public class AlmApplicationProviderModel {
    
    private String applicationId;
    private String name;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String id) {
        this.applicationId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
