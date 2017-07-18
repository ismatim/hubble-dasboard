package hubble.frontend.managers.models;

public class AppPulseActiveBusinessApplication {

    private int id;
    private String businessApplicationName;
    private String businessApplicationDisplayName;

    public AppPulseActiveBusinessApplication() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessApplicationName() {
        return businessApplicationName;
    }

    public void setBusinessApplicationName(String businessApplicationName) {
        this.businessApplicationName = businessApplicationName;
    }

    public String getBusinessApplicationDisplayName() {
        return businessApplicationDisplayName;
    }

    public void setBusinessApplicationDisplayName(String businessApplicationDisplayName) {
        this.businessApplicationDisplayName = businessApplicationDisplayName;
    }
}
