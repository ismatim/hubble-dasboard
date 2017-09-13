package hubble.backend.business.services.implementations.services;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.interfaces.operations.IssueOperations;
import hubble.backend.business.services.interfaces.services.IssueService;
import hubble.backend.business.services.models.IssueDto;
import hubble.backend.business.services.models.measures.IssuesQuantity;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.storage.models.IssueStorage;
import hubble.backend.storage.repositories.IssueRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IssueServiceImpl implements IssueService {

    @Autowired
    IssueRepository issueRepository;
    @Autowired
    MapperConfiguration mapper;
    @Autowired
    IssueOperations issueOperation;

    @Override
    public List<IssueDto> getLastDay(String applicationId) {

        Calendar yesterday = CalendarHelper.getNow();
        yesterday.add(Calendar.HOUR, -24);
        Date today = new GregorianCalendar().getTime();

        List<IssueStorage> issues = issueRepository.findIssuesByApplicationIdBetweenDates(applicationId, yesterday.getTime(), today);

        return mapper.mapToIssueDtoList(issues);
    }

    @Override
    public List<IssueDto> getLastMonth(String applicationId) {
        Calendar lastmonth = CalendarHelper.getNow();
        lastmonth.add(Calendar.MONTH, -1);
        Date today = new GregorianCalendar().getTime();

        List<IssueStorage> issues = issueRepository.findIssuesByApplicationIdBetweenDates(applicationId, lastmonth.getTime(), today);

        return mapper.mapToIssueDtoList(issues);
    }

    @Override
    public IssuesQuantity calculateIssuesQuantityLastMonth(String applicationId) {
        return issueOperation.calculateIssuesQuantityLastMonth(applicationId);
    }

    @Override
    public IssuesQuantity calculateIssuesQuantityLastDay(String applicationId) {
        return issueOperation.calculateIssuesQuantityLastDay(applicationId);
    }

}
