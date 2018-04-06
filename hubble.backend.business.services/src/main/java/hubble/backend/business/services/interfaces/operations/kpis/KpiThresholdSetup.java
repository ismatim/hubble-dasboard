package hubble.backend.business.services.interfaces.operations.kpis;

public interface KpiThresholdSetup {

    //Kpis
    public double getWarningKpiThreshold();

    public void setWarningKpiThreshold(double threshold);

    public double getCriticalKpiThreshold();

    public void setCriticalKpiThreshold(double threshold);

    //Indexes
    public double getWarningIdxThreshold();

    public void setWarningIdxThreshold(double threshold);

    public double getCriticalIdxThreshold();

    public void setCriticalIdxThreshold(double threshold);
}
