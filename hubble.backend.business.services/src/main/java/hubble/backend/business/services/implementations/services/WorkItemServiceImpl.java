package hubble.backend.business.services.implementations.services;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.interfaces.operations.WorkItemOperations;
import hubble.backend.business.services.interfaces.services.WorkItemService;
import hubble.backend.business.services.models.WorkItemDto;
import hubble.backend.business.services.models.measures.WorkItemQuantity;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.storage.models.WorkItemStorage;
import hubble.backend.storage.repositories.WorkItemRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkItemServiceImpl implements WorkItemService {

    @Autowired
    WorkItemRepository workItemRepository;
    @Autowired
    MapperConfiguration mapper;
    @Autowired
    WorkItemOperations workItemOperation;

    @Override
    public List<WorkItemDto> getLastDay(String applicationId) {

        Calendar yesterday = CalendarHelper.getNow();
        yesterday.add(Calendar.HOUR, -24);
        Date today = new GregorianCalendar().getTime();

        List<WorkItemStorage> issues = workItemRepository.findWorkItemsByApplicationIdBetweenDates(applicationId, yesterday.getTime(), today);

        return mapper.mapToWorkItemDtoList(issues);
    }

    @Override
    public List<WorkItemDto> getLastMonth(String applicationId) {
        Calendar lastmonth = CalendarHelper.getNow();
        lastmonth.add(Calendar.MONTH, -1);
        Date today = new GregorianCalendar().getTime();

        List<WorkItemStorage> workitems = workItemRepository.findWorkItemsByApplicationIdBetweenDates(applicationId, lastmonth.getTime(), today);

        return mapper.mapToWorkItemDtoList(workitems);
    }

    @Override
    public WorkItemQuantity calculateWorkItemQuantityLastMonth(String applicationId) {
        return workItemOperation.calculateWorkItemQuantityLastMonth(applicationId);
    }

    @Override
    public WorkItemQuantity calculateWorkItemQuantityLastDay(String applicationId) {
        return workItemOperation.calculateWorkItemQuantityLastDay(applicationId);
    }

}
