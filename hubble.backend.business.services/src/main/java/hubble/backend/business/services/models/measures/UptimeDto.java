package hubble.backend.business.services.models.measures;

import hubble.backend.business.services.models.TransactionDto;
import hubble.backend.core.enums.MonitoringFields;
import java.util.Date;
import java.util.TreeMap;

public class UptimeDto {

    private MonitoringFields.FRECUENCY frecuency;
    private TreeMap<Date, Integer> uptimes;
    private TransactionDto transactionMeasured;

    public MonitoringFields.FRECUENCY getPeriod() {
        return frecuency;
    }

    public void setPeriod(MonitoringFields.FRECUENCY period) {
        this.frecuency = period;
    }

    public TreeMap<Date, Integer> getUptimes() {
        return uptimes;
    }

    public void setUptimes(TreeMap<Date, Integer> uptimes) {
        this.uptimes = uptimes;
    }

    public TransactionDto getTransactionMeasured() {
        return transactionMeasured;
    }

    public void setTransactionMeasured(TransactionDto transactionMeasured) {
        this.transactionMeasured = transactionMeasured;
    }
}
