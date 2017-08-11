package hubble.frontend.managers.models.aggregations;

import hubble.backend.core.enums.MonitoringFields;
import hubble.frontend.managers.models.entities.BusinessApplication;
import hubble.frontend.managers.models.entities.Transaction;

public class AvailabilityTransactionAvg {

    private BusinessApplication businessApplication;
    private Transaction transaction;
    private MonitoringFields.STATUS Status;
    private int average;

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

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }
}
