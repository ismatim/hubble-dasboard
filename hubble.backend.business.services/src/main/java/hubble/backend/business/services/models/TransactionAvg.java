package hubble.backend.business.services.models;

import hubble.backend.core.enums.MonitoringFields;

public class TransactionAvg extends Transaction {

    private MonitoringFields.STATUS Status;
    private Double average;

    public TransactionAvg() {
        this.average = null;
        this.Status = MonitoringFields.STATUS.NO_DATA;
    }

    public MonitoringFields.STATUS getStatus() {
        return Status;
    }

    public void setStatus(MonitoringFields.STATUS Status) {
        this.Status = Status;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }
}
