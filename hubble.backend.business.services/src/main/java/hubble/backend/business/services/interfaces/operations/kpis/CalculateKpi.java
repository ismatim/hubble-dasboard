package hubble.backend.business.services.interfaces.operations.kpis;

public interface CalculateKpi {

    public double calculateIndex();

    public double getWarningKpiThreshold();

    public void setWarningKpiThreshold(double warningKpiThreshold);

    public double getCriticalKpiThreshold();

    public void setCriticalKpiThreshold(double criticalKpiThreshold);

    public double getWarningIdxThreshold();

    public void setWarningIdxThreshold(double warningIdxThreshold);

    public double getCriticalIdxThreshold();

    public void setCriticalIdxThreshold(double criticalIdxThreshold);

    public double getValue();

    public void setValue(int value);
}
