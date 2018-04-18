package hubble.backend.business.services.implementations.operations;

import hubble.backend.business.services.interfaces.operations.*;
import hubble.backend.business.services.models.measures.quantities.WorkItemQuantity;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.core.utils.Threshold;
import hubble.backend.storage.models.WorkItemStorage;
import hubble.backend.storage.repositories.WorkItemRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkItemOperationsImpl implements WorkItemOperations {

    @Autowired
    WorkItemRepository workItemRepository;

    private Double warningKpiThreshold;
    private Double criticalKpiThreshold;
    private Double warningIdxThreshold;
    private Double criticalIdxThreshold;

    @Override
    public WorkItemQuantity calculateWorkItemQuantityLastMonth(String applicationId) {
        WorkItemQuantity workitemsQty = new WorkItemQuantity();
        Calendar lastmonth = CalendarHelper.getNow();
        lastmonth.add(Calendar.MONTH, -1);
        Date today = new GregorianCalendar().getTime();

        List<WorkItemStorage> workItems = workItemRepository.findWorkItemsByApplicationIdBetweenDates(applicationId, lastmonth.getTime(), today);

        setRangeKpiLastMonth();
        workitemsQty.setQuantity(workItems.size());

        calculateStatus(workitemsQty);

        return workitemsQty;
    }

    @Override
    public WorkItemQuantity calculateWorkItemQuantityLastWeek(String applicationId) {
        WorkItemQuantity workitemsQty = new WorkItemQuantity();
        Calendar yesterday = CalendarHelper.getNow();
        yesterday.add(Calendar.HOUR, -24);
        Date today = new GregorianCalendar().getTime();

        List<WorkItemStorage> workitems = workItemRepository.findWorkItemsByApplicationIdBetweenDates(applicationId, yesterday.getTime(), today);

        setRangeKpiLastWeek();
        workitemsQty.setQuantity(workitems.size());
        calculateStatus(workitemsQty);
        return workitemsQty;
    }

    private void calculateStatus(WorkItemQuantity workItemsQty) {

        if (workItemsQty.getQuantity() <= this.warningKpiThreshold) {
            workItemsQty.setCriticalThreshold(this.criticalKpiThreshold.intValue());
            workItemsQty.setStatus(MonitoringFields.STATUS.SUCCESS);
            return;
        } else if (workItemsQty.getQuantity() > this.warningKpiThreshold
                && workItemsQty.getQuantity() < this.criticalKpiThreshold.intValue()) {
            workItemsQty.setCriticalThreshold(this.criticalKpiThreshold.intValue());
            workItemsQty.setStatus(MonitoringFields.STATUS.WARNING);
            return;
        }

        workItemsQty.setCriticalThreshold(workItemsQty.getQuantity());
        workItemsQty.setStatus(MonitoringFields.STATUS.CRITICAL);
    }

    @Override
    public double getWarningKpiThreshold() {
        return warningKpiThreshold;
    }

    @Override
    public void setWarningKpiThreshold(double warningKpiThreshold) {
        this.warningKpiThreshold = warningKpiThreshold;
    }

    @Override
    public double getCriticalKpiThreshold() {
        return criticalKpiThreshold;
    }

    @Override
    public void setCriticalKpiThreshold(double criticalKpiThreshold) {
        this.criticalKpiThreshold = criticalKpiThreshold;
    }

    @Override
    public double getWarningIdxThreshold() {
        return warningIdxThreshold;
    }

    @Override
    public void setWarningIdxThreshold(double warningIdxThreshold) {
        this.warningIdxThreshold = warningIdxThreshold;
    }

    @Override
    public double getCriticalIdxThreshold() {
        return criticalIdxThreshold;
    }

    @Override
    public void setCriticalIdxThreshold(double criticalIdxThreshold) {
        this.criticalIdxThreshold = criticalIdxThreshold;
    }

    private void setRangeKpiLastWeek() {

        this.warningKpiThreshold = (double) Threshold.WorkItems.WARNING_WORKITEMS_WEEK_DEFAULT;
        this.criticalKpiThreshold = (double) Threshold.WorkItems.CRITICAL_WORKITEMS_WEEK_DEFAULT;
    }

    private void setRangeKpiLastMonth() {

        this.warningKpiThreshold = (double) Threshold.WorkItems.WARNING_WORKITEMS_MONTH_DEFAULT;
        this.criticalKpiThreshold = (double) Threshold.WorkItems.CRITICAL_WORKITEMS_MONTH_DEFAULT;
    }
}
