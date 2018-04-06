package hubble.backend.business.services.models.measures;

import hubble.backend.business.services.models.Application;
import hubble.backend.business.services.models.Transaction;
import hubble.backend.core.enums.MonitoringFields;
import java.util.Date;
import java.util.TreeMap;

public class Uptime extends Average {

    private MonitoringFields.FRECUENCY frecuency;
    private TreeMap<Date, Integer> uptimes;
    private Transaction transactionMeasured;
    private Application applicationMeasured;

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

    public Transaction getTransactionMeasured() {
        return transactionMeasured;
    }

    public void setTransactionMeasured(Transaction transactionMeasured) {
        this.transactionMeasured = transactionMeasured;
    }

    public Application getApplicationMeasured() {
        return applicationMeasured;
    }

    public void setApplicationMeasured(Application applicationMeasured) {
        this.applicationMeasured = applicationMeasured;
    }
}
