package hubble.backend.business.services.implementations.operations;

import hubble.backend.business.services.interfaces.operations.*;
import hubble.backend.business.services.models.measures.IssuesQuantity;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.core.utils.Threshold;
import hubble.backend.storage.models.IssueStorage;
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

    @Override
    public IssuesQuantity calculateIssuesQuantityLastMonth(String applicationId) {
        IssuesQuantity issuesQty = new IssuesQuantity();
        Calendar lastmonth = CalendarHelper.getNow();
        lastmonth.add(Calendar.MONTH, -1);
        Date today = new GregorianCalendar().getTime();

        List<IssueStorage> issues = issueRepository.findIssuesByApplicationIdBetweenDates(applicationId, lastmonth.getTime(), today);

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

        issuesQty.setQuantity(issues.size());
        calculateStatus(issuesQty);
        return issuesQty;
    }

    private void calculateStatus(IssuesQuantity issuesQty) {

        if (issuesQty.getQuantity() < 10) {
            issuesQty.setCriticalThreshold(Threshold.CRITICAL_TASKS_DEFAULT);
            issuesQty.setStatus(MonitoringFields.STATUS.SUCCESS);
            return;
        }

        issuesQty.setCriticalThreshold(issuesQty.getQuantity());
        issuesQty.setStatus(MonitoringFields.STATUS.CRITICAL);
    }
}
