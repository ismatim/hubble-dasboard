package hubble.frontend.business.models;

import hubble.backend.core.enums.MonitoringFields;

public class TransactionAvg {

    private Transaction transaction;
    private MonitoringFields.STATUS Status;
    private Integer average;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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
