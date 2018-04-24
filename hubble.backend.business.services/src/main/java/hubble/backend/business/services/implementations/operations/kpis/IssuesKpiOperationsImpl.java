package hubble.backend.business.services.implementations.operations.kpis;

import hubble.backend.business.services.interfaces.operations.kpis.CalculateKpiLowNumberBestIndicator;
import hubble.backend.business.services.interfaces.operations.kpis.IssuesKpiOperations;
import hubble.backend.business.services.interfaces.operations.rules.IssuesGroupRuleOperations;
import hubble.backend.business.services.models.measures.kpis.IssuesKpi;
import hubble.backend.business.services.models.measures.rules.IssuesGroupRule;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.core.utils.KpiHelper;
import hubble.backend.core.utils.Threshold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IssuesKpiOperationsImpl implements IssuesKpiOperations {

    @Autowired
    CalculateKpiLowNumberBestIndicator calculateIssuesKpi;
    @Autowired
    IssuesGroupRuleOperations issuesRulesOperation;

    private double warningKpiThreshold;
    private double criticalKpiThreshold;
    private double warningIdxThreshold;
    private double criticalIdxThreshold;

    @Override
    public double calculateKeyPerformanceIndicator(IssuesGroupRule issuesGroupRule) {

        if (issuesGroupRule == null) {
            return 0d;
        }

        calculateIssuesKpi.setWarningKpiThreshold(this.warningKpiThreshold);
        calculateIssuesKpi.setCriticalKpiThreshold(this.criticalKpiThreshold);
        calculateIssuesKpi.setWarningIdxThreshold(this.warningIdxThreshold);
        calculateIssuesKpi.setCriticalIdxThreshold(this.criticalIdxThreshold);
        calculateIssuesKpi.setValue(issuesGroupRule.get());

        return calculateIssuesKpi.calculateIndex();
    }

    @Override
    public IssuesKpi calculateLast10MinutesKeyPerformanceIndicatorByApplication(String applicationId) {

        IssuesGroupRule availabilityLastHourRuleGroup = issuesRulesOperation.calculateLast10MinutesGroupRuleByApplication(applicationId);

        setKpiThresholdFor10Minutes();

        double kpi10Min = calculateKeyPerformanceIndicator(availabilityLastHourRuleGroup);

        IssuesKpi issuesKpi = new IssuesKpi();
        issuesKpi.set(kpi10Min);

        issuesKpi.setStatus(calculateKpiStatus(kpi10Min));
        return issuesKpi;
    }

    @Override
    public IssuesKpi calculateLastHourKeyPerformanceIndicatorByApplication(String applicationId) {

        IssuesGroupRule availabilityLastHourRuleGroup = issuesRulesOperation.calculateLastHourGroupRuleByApplication(applicationId);

        setKpiThresholdForHour();

        double kpiLastHour = calculateKeyPerformanceIndicator(availabilityLastHourRuleGroup);

        IssuesKpi issuesKpi = new IssuesKpi();
        issuesKpi.set(kpiLastHour);

        issuesKpi.setStatus(calculateKpiStatus(kpiLastHour));
        return issuesKpi;

    }

    @Override
    public IssuesKpi calculateLastDayKeyPerformanceIndicatorByApplication(String applicationId) {

        IssuesGroupRule availabilityLastDayRuleGroup = issuesRulesOperation.calculateLastDayGroupRuleByApplication(applicationId);

        setKpiThresholdForDay();

        double kpiLastDay = calculateKeyPerformanceIndicator(availabilityLastDayRuleGroup);

        IssuesKpi issuesKpi = new IssuesKpi();
        issuesKpi.set(kpiLastDay);

        issuesKpi.setStatus(calculateKpiStatus(kpiLastDay));
        return issuesKpi;

    }

    @Override
    public IssuesKpi calculateLastMonthKeyPerformanceIndicatorByApplication(String applicationId) {
        IssuesGroupRule availabilityLastMonthRuleGroup = issuesRulesOperation.calculateLastMonthGroupRuleByApplication(applicationId);

        setKpiThresholdForMonth();

        double kpiLastMonth = calculateKeyPerformanceIndicator(availabilityLastMonthRuleGroup);

        IssuesKpi issuesKpi = new IssuesKpi();
        issuesKpi.set(kpiLastMonth);

        issuesKpi.setStatus(calculateKpiStatus(kpiLastMonth));
        return issuesKpi;
    }

    @Override
    public MonitoringFields.STATUS calculateKpiStatus(Double measure) {

        if (measure == 0) {
            return MonitoringFields.STATUS.NO_DATA;
        }

        //TODO: Determinará el Status del KPI de Disponibilidad.
        if (measure <= this.warningKpiThreshold) {
            return MonitoringFields.STATUS.SUCCESS;
        } else if (measure > this.warningKpiThreshold
                && measure < this.criticalKpiThreshold) {
            return MonitoringFields.STATUS.WARNING;
        } else if (measure > this.criticalKpiThreshold) {
            return MonitoringFields.STATUS.CRITICAL;
        }
        return MonitoringFields.STATUS.NO_DATA;
    }

    @Override
    public double getWarningKpiThreshold() {
        return this.warningKpiThreshold;
    }

    @Override
    public void setWarningKpiThreshold(double threshold) {
        this.warningKpiThreshold = threshold;
    }

    @Override
    public double getCriticalKpiThreshold() {
        return this.criticalKpiThreshold;
    }

    @Override
    public void setCriticalKpiThreshold(double threshold) {
        this.criticalKpiThreshold = threshold;
    }

    @Override
    public double getWarningIdxThreshold() {
        return this.warningIdxThreshold;
    }

    @Override
    public void setWarningIdxThreshold(double threshold) {
        this.warningIdxThreshold = threshold;
    }

    @Override
    public double getCriticalIdxThreshold() {
        return this.criticalIdxThreshold;
    }

    @Override
    public void setCriticalIdxThreshold(double threshold) {
        this.criticalIdxThreshold = threshold;
    }

    //Privates Methods
    private void setKpiThresholdFor10Minutes() {
        this.warningKpiThreshold = Threshold.Issues.WARNING_ISSUES_10_MIN_DEFAULT;
        this.criticalKpiThreshold = Threshold.Issues.CRITICAL_ISSUES_10_MIN_DEFAULT;
        this.warningIdxThreshold = KpiHelper.Issues.WARNING_ISSUES_KPI_DEFAULT;
        this.criticalIdxThreshold = KpiHelper.Issues.CRITICAL_ISSUES_KPI_DEFAULT;
    }

    private void setKpiThresholdForHour() {
        this.warningKpiThreshold = Threshold.Issues.WARNING_ISSUES_DAY_DEFAULT;
        this.criticalKpiThreshold = Threshold.Issues.CRITICAL_ISSUES_DAY_DEFAULT;
        this.warningIdxThreshold = KpiHelper.Issues.WARNING_ISSUES_KPI_DEFAULT;
        this.criticalIdxThreshold = KpiHelper.Issues.CRITICAL_ISSUES_KPI_DEFAULT;
    }

    private void setKpiThresholdForDay() {
        this.warningKpiThreshold = Threshold.Issues.WARNING_ISSUES_DAY_DEFAULT;
        this.criticalKpiThreshold = Threshold.Issues.CRITICAL_ISSUES_DAY_DEFAULT;
        this.warningIdxThreshold = KpiHelper.Issues.WARNING_ISSUES_KPI_DEFAULT;
        this.criticalIdxThreshold = KpiHelper.Issues.CRITICAL_ISSUES_KPI_DEFAULT;
    }

    private void setKpiThresholdForMonth() {
        this.warningKpiThreshold = Threshold.Issues.WARNING_ISSUES_MONTH_DEFAULT;
        this.criticalKpiThreshold = Threshold.Issues.CRITICAL_ISSUES_MONTH_DEFAULT;
        this.warningIdxThreshold = KpiHelper.Issues.WARNING_ISSUES_KPI_DEFAULT;
        this.criticalIdxThreshold = KpiHelper.Issues.CRITICAL_ISSUES_KPI_DEFAULT;
    }
}
