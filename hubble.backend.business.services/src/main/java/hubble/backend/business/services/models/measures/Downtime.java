package hubble.backend.business.services.models.measures;

import hubble.backend.business.services.models.Transaction;
import java.util.Date;
import java.util.Map;

public class Downtime extends Status {

    private Map<Date, Integer> downtimes;
    private Transaction transactionMeasured;

    public Map<Date, Integer> getDowntimes() {
        return downtimes;
    }

    public void setDowntimes(Map<Date, Integer> downtimes) {
        this.downtimes = downtimes;
    }

    public Transaction getTransactionMeasured() {
        return transactionMeasured;
    }

    public void setTransactionMeasured(Transaction transactionMeasured) {
        this.transactionMeasured = transactionMeasured;
    }
}
