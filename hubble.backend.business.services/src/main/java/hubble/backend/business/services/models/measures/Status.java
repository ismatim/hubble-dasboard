package hubble.backend.business.services.models.measures;

import hubble.backend.core.enums.MonitoringFields;

public abstract class Status {

    private MonitoringFields.STATUS Status;

    public Status() {
        this.Status = MonitoringFields.STATUS.NO_DATA;
    }

    public MonitoringFields.STATUS getStatus() {
        return Status;
    }

    public void setStatus(MonitoringFields.STATUS Status) {
        this.Status = Status;
    }
}
