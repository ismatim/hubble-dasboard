package hubble.backend.business.services.implementations.operations;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.interfaces.operations.averages.IssueOperations;
import hubble.backend.business.services.models.measures.quantities.IssuesQuantity;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.core.utils.Threshold;
import hubble.backend.storage.models.IssueStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import hubble.backend.storage.repositories.IssueRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IssueOperationsImpl implements IssueOperations {

    @Autowired
    IssueRepository issueRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    MapperConfiguration mapper;

    private Double warningKpiThreshold;
    private Double criticalKpiThreshold;
    private Double warningIdxThreshold;
    private Double criticalIdxThreshold;

    @Override
    public IssuesQuantity calculateIssuesQuantityLastMonth(String applicationId) {
        IssuesQuantity issuesQty = new IssuesQuantity();
        Calendar lastmonth = CalendarHelper.getNow();
        lastmonth.add(Calendar.MONTH, -1);
        Date today = new GregorianCalendar().getTime();

        List<IssueStorage> issues = issueRepository.findIssuesByApplicationIdBetweenDates(applicationId, lastmonth.getTime(), today);
        setRangeKpiLastMonth();
        issuesQty.setQuantity(issues.size());
        calculateStatus(issuesQty);

        return issuesQty;
    }

    @Override
    public IssuesQuantity calculateIssuesQuantityLastDay(String applicationId) {
        IssuesQuantity issuesQty = new IssuesQuantity();
        Calendar yesterday = CalendarHelper.getNow();
        yesterday.add(Calendar.HOUR, -24);
        Date today = new GregorianCalendar().getTime();

        List<IssueStorage> issues = issueRepository.findIssuesByApplicationIdBetweenDates(applicationId, yesterday.getTime(), today);
        setRangeKpiLastDay();

        issuesQty.setQuantity(issues.size());
        calculateStatus(issuesQty);
        return issuesQty;
    }

    private void calculateStatus(IssuesQuantity issuesQty) {

        if (issuesQty.getQuantity() <= this.warningKpiThreshold) {
            issuesQty.setCriticalThreshold(this.criticalKpiThreshold.intValue());
            issuesQty.setStatus(MonitoringFields.STATUS.SUCCESS);
            return;
        } else if (issuesQty.getQuantity() > this.warningKpiThreshold
                && issuesQty.getQuantity() < this.criticalKpiThreshold.intValue()) {
            issuesQty.setCriticalThreshold(this.criticalKpiThreshold.intValue());
            issuesQty.setStatus(MonitoringFields.STATUS.WARNING);
            return;
        }

        issuesQty.setCriticalThreshold(issuesQty.getQuantity());
        issuesQty.setStatus(MonitoringFields.STATUS.CRITICAL);
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

    private void setRangeKpiLastDay() {

        this.warningKpiThreshold = (double) Threshold.Issues.WARNING_ISSUES_DAY_DEFAULT;
        this.criticalKpiThreshold = (double) Threshold.Issues.CRITICAL_ISSUES_DAY_DEFAULT;
    }

    private void setRangeKpiLastMonth() {

        this.warningKpiThreshold = (double) Threshold.Issues.WARNING_ISSUES_MONTH_DEFAULT;
        this.criticalKpiThreshold = (double) Threshold.Issues.CRITICAL_ISSUES_MONTH_DEFAULT;
    }
}
