package hubble.backend.business.services.models.measures;

import hubble.backend.business.services.models.TransactionDto;
import java.util.Date;
import java.util.Map;

public class DowntimeDto extends Status {

    private Map<Date, Integer> downtimes;
    private TransactionDto transactionMeasured;

    public Map<Date, Integer> getDowntimes() {
        return downtimes;
    }

    public void setDowntimes(Map<Date, Integer> downtimes) {
        this.downtimes = downtimes;
    }

    public TransactionDto getTransactionMeasured() {
        return transactionMeasured;
    }

    public void setTransactionMeasured(TransactionDto transactionMeasured) {
        this.transactionMeasured = transactionMeasured;
    }
}
