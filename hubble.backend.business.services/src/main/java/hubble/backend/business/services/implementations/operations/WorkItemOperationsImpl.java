package hubble.backend.business.services.implementations.operations;

import hubble.backend.business.services.interfaces.operations.*;
import hubble.backend.business.services.models.measures.WorkItemQuantity;
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

    @Override
    public WorkItemQuantity calculateWorkItemQuantityLastMonth(String applicationId) {
        WorkItemQuantity workitemsQty = new WorkItemQuantity();
        Calendar lastmonth = CalendarHelper.getNow();
        lastmonth.add(Calendar.MONTH, -1);
        Date today = new GregorianCalendar().getTime();

        List<WorkItemStorage> workItems = workItemRepository.findWorkItemsByApplicationIdBetweenDates(applicationId, lastmonth.getTime(), today);

        workitemsQty.setQuantity(workItems.size());

        calculateStatus(workitemsQty);

        return workitemsQty;
    }

    @Override
    public WorkItemQuantity calculateWorkItemQuantityLastDay(String applicationId) {
        WorkItemQuantity workitemsQty = new WorkItemQuantity();
        Calendar yesterday = CalendarHelper.getNow();
        yesterday.add(Calendar.HOUR, -24);
        Date today = new GregorianCalendar().getTime();

        List<WorkItemStorage> workitems = workItemRepository.findWorkItemsByApplicationIdBetweenDates(applicationId, yesterday.getTime(), today);

        workitemsQty.setQuantity(workitems.size());
        calculateStatus(workitemsQty);
        return workitemsQty;
    }

    private void calculateStatus(WorkItemQuantity workItemsQty) {

        if (workItemsQty.getQuantity() < 10) {
            workItemsQty.setCriticalThreshold(Threshold.CRITICAL_WORK_ITEMS_DEFAULT);
            workItemsQty.setStatus(MonitoringFields.STATUS.SUCCESS);
            return;
        }

        workItemsQty.setCriticalThreshold(workItemsQty.getQuantity());
        workItemsQty.setStatus(MonitoringFields.STATUS.CRITICAL);
    }
}
