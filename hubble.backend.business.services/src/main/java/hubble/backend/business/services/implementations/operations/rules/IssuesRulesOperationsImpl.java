package hubble.backend.business.services.implementations.operations.rules;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.interfaces.operations.rules.IssuesGroupRuleOperations;
import hubble.backend.business.services.models.business.ApplicationIndicators;
import hubble.backend.business.services.models.measures.rules.IssuesGroupRule;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.core.utils.Threshold;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.IssueStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import hubble.backend.storage.repositories.IssueRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IssuesRulesOperationsImpl implements IssuesGroupRuleOperations {

    @Autowired
    IssueRepository issuesRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    MapperConfiguration mapper;

    private double warningKpiThreshold;
    private double criticalKpiThreshold;
    private double warningIdxThreshold;
    private double criticalIdxThreshold;

    @Override
    public Double calculateGroupRule(List<IssueStorage> issues) {
        double totalPoints = 0;

        for (int i = 1; i <= Threshold.Issues.TOTAL_LEVELS_OF_SEVERITY; i++) {
            int severity = i;
            totalPoints += issues.stream().filter(x -> x.getSeverity() == severity).count() * severity;
        }

        return (Double) totalPoints;
    }

    @Override
    public IssuesGroupRule calculateLast10MinutesGroupRuleByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);

        ApplicationIndicators applicationAvailabilityIndicators = mapper.mapToApplicationIndicatorsDto(applicationStorage);

        List<IssueStorage> issues = issuesRepository.findIssuesByApplicationIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, applicationId);

        Double issuesKpi = calculateGroupRule(issues);

        IssuesGroupRule issueGroupRule = new IssuesGroupRule();

        setRangeKpi10Min();
        issueGroupRule.setStatus(calculateGroupRuleStatus(applicationAvailabilityIndicators, issuesKpi));
        issueGroupRule.set(issuesKpi.intValue());

        return issueGroupRule;
    }

    @Override
    public IssuesGroupRule calculateLastHourGroupRuleByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);

        ApplicationIndicators applicationAvailabilityIndicators = mapper.mapToApplicationIndicatorsDto(applicationStorage);

        List<IssueStorage> issues = issuesRepository.findIssuesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_HOUR, applicationId);

        Double issuesKpi = calculateGroupRule(issues);

        IssuesGroupRule issueGroupRule = new IssuesGroupRule();

        setRangeKpi1Hour();
        issueGroupRule.setStatus(calculateGroupRuleStatus(applicationAvailabilityIndicators, issuesKpi));
        issueGroupRule.set(issuesKpi.intValue());

        return issueGroupRule;
    }

    @Override
    public IssuesGroupRule calculateLastDayGroupRuleByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);

        ApplicationIndicators applicationAvailabilityIndicators = mapper.mapToApplicationIndicatorsDto(applicationStorage);

        List<IssueStorage> issues = issuesRepository.findIssuesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_DAY, applicationId);

        Double issuesKpi = calculateGroupRule(issues);

        IssuesGroupRule issueGroupRule = new IssuesGroupRule();

        setRangeKpiLastDay();
        issueGroupRule.setStatus(calculateGroupRuleStatus(applicationAvailabilityIndicators, issuesKpi));
        issueGroupRule.set(issuesKpi.intValue());

        return issueGroupRule;
    }

    @Override
    public IssuesGroupRule calculateLastMonthGroupRuleByApplication(String applicationId) {

        List<IssueStorage> issues = issuesRepository.findIssuesByApplicationIdAndDurationMonths(1, applicationId);

        Double issuesGroupRule = calculateGroupRule(issues);

        IssuesGroupRule issueGroupRule = new IssuesGroupRule();

        setRangeKpiLastMonth();
        issueGroupRule.setStatus(calculateGroupRuleStatus(null, issuesGroupRule));
        issueGroupRule.set(issuesGroupRule.intValue());

        return issueGroupRule;
    }

    @Override
    public MonitoringFields.STATUS calculateGroupRuleStatus(ApplicationIndicators appIndicator, Double measure) {

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

    private void setRangeKpi10Min() {

        this.warningKpiThreshold = Threshold.Issues.WARNING_ISSUES_10_MIN_DEFAULT;
        this.criticalKpiThreshold = Threshold.Issues.CRITICAL_ISSUES_10_MIN_DEFAULT;
    }

    private void setRangeKpi1Hour() {

        this.warningKpiThreshold = Threshold.Issues.WARNING_ISSUES_HOUR_DEFAULT;
        this.criticalKpiThreshold = Threshold.Issues.CRITICAL_ISSUES_HOUR_DEFAULT;
    }

    private void setRangeKpiLastDay() {

        this.warningKpiThreshold = Threshold.Issues.WARNING_ISSUES_DAY_DEFAULT;
        this.criticalKpiThreshold = Threshold.Issues.WARNING_ISSUES_DAY_DEFAULT;
    }

    private void setRangeKpiLastMonth() {

        this.warningKpiThreshold = Threshold.Issues.WARNING_ISSUES_MONTH_DEFAULT;
        this.criticalKpiThreshold = Threshold.Issues.CRITICAL_ISSUES_MONTH_DEFAULT;
    }
}
