package hubble.backend.business.services.models;

import hubble.backend.core.enums.MonitoringFields;

public class TransactionAvgDto extends TransactionDto {

    private MonitoringFields.STATUS Status;
    private Integer average;

    public TransactionAvgDto() {
        this.average = null;
        this.Status = MonitoringFields.STATUS.NO_DATA;
    }

    public MonitoringFields.STATUS getStatus() {
        return Status;
    }

    public void setStatus(MonitoringFields.STATUS Status) {
        this.Status = Status;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }
}
