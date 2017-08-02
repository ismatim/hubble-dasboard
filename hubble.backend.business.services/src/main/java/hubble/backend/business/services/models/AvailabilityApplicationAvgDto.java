
package hubble.backend.business.services.models;

import hubble.backend.core.enums.MonitoringFields;

public class AvailabilityApplicationAvgDto extends ApplicationDto {

    private MonitoringFields.STATUS Status;
    private int average;

    public MonitoringFields.STATUS getStatus() {
        return Status;
    }

    public void setStatus(MonitoringFields.STATUS Status) {
        this.Status = Status;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }
    
}
