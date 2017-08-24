package hubble.frontend.managers.models.aggregations;

import hubble.backend.core.enums.MonitoringFields;
import hubble.frontend.managers.models.entities.BusinessApplication;

public class BusinessApplicationAvg {

    private BusinessApplication businessApplication;
    private MonitoringFields.STATUS Status;
    private Float average;

    public BusinessApplication getBusinessApplication() {
        return businessApplication;
    }

    public void setBusinessApplication(BusinessApplication businessApplication) {
        this.businessApplication = businessApplication;
    }

    public MonitoringFields.STATUS getStatus() {
        return Status;
    }

    public void setStatus(MonitoringFields.STATUS Status) {
        this.Status = Status;
    }

    public Float getAverage() {
        return average;
    }

    public void setAverage(Float average) {
        this.average = average;
    }

}
