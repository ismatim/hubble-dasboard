package hubble.backend.providers.models.apppulse;

import java.util.ArrayList;

public class ApplicationProviderModel {

    private String tenantId;
    private ArrayList<ApplicationData> applications;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public ArrayList<ApplicationData> getApplications() {
        return applications;
    }

    public void setApplications(ArrayList<ApplicationData> applications) {
        this.applications = applications;
    }
}
