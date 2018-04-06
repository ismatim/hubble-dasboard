package hubble.backend.api.implementations;

import hubble.backend.api.configurations.mappers.ApplicationMapper;
import hubble.backend.api.configurations.mappers.UptimeMapper;
import hubble.backend.api.interfaces.BusinessApplicationManager;
import hubble.backend.api.models.ApplicationUptime;
import hubble.backend.api.models.BusinessApplication;
import hubble.backend.api.models.BusinessApplicationProfile;
import hubble.backend.business.services.interfaces.services.AvailabilityService;
import hubble.backend.business.services.interfaces.services.IssueService;
import hubble.backend.business.services.interfaces.services.PerformanceService;
import hubble.backend.business.services.interfaces.services.UptimeDowntimeService;
import hubble.backend.business.services.interfaces.services.WorkItemService;
import hubble.backend.business.services.models.Application;
import hubble.backend.business.services.models.Availability;
import hubble.backend.business.services.models.business.ApplicationIndicators;
import hubble.backend.business.services.models.measures.IssuesQuantity;
import hubble.backend.business.services.models.measures.Uptime;
import hubble.backend.business.services.models.measures.WorkItemQuantity;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.core.utils.CalendarHelper;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessApplicationManagerImpl implements BusinessApplicationManager {

    @Autowired
    AvailabilityService availabilityService;
    @Autowired
    PerformanceService performanceService;
    @Autowired
    IssueService issueService;
    @Autowired
    ApplicationMapper applicationMapper;
    @Autowired
    UptimeDowntimeService uptimeService;
    @Autowired
    WorkItemService workItemService;
    @Autowired
    UptimeMapper uptimeMapper;

    @Override
    public BusinessApplicationProfile getBusinessApplicationView(String id) {

        BusinessApplicationProfile businessView = new BusinessApplicationProfile();
        businessView.setId(id);

        //10 minuntes
        ApplicationIndicators availabilityAvg10min = availabilityService.calculateLast10MinutesAverageByApplication(id);
        ApplicationIndicators performanceAvg10min = performanceService.calculateLast10MinutesAverageByApplication(id);

        if (availabilityAvg10min == null || performanceAvg10min == null) {
            return businessView;
        }

        businessView.setAvailabilityAverage10min(availabilityAvg10min.getAvailabilityAverageValue());
        businessView.setPerformanceAverage10min(performanceAvg10min.getPerformanceAverageValue());
        businessView.setStatusAvailability10min(availabilityAvg10min.getAvailabilityAverage().getStatus().toString());
        businessView.setStatusPerformance10min(performanceAvg10min.getPerformanceAverage().getStatus().toString());
        businessView.setMeasuresQtyAvailability10min(availabilityAvg10min.getMeasuresQtyAvailability().getQuantity());
        businessView.setMeasuresQtyPerformance10min(performanceAvg10min.getMeasuresQtyPerformance().getQuantity());

        businessView.setAvailabilityCriticalValue10min(availabilityAvg10min.getAvailabilityThreshold());
        businessView.setPerformanceCriticalValue10min(performanceAvg10min.getCriticalThreshold());

        //1 Hour
        ApplicationIndicators availabilityAvg1Hour = availabilityService.calculateLastHourAverageByApplication(id);
        ApplicationIndicators performanceAvg1Hour = performanceService.calculateLastHourAverageByApplication(id);

        businessView.setAvailabilityAverage1hour(availabilityAvg1Hour.getAvailabilityAverageValue());
        businessView.setPerformanceAverage1hour(performanceAvg1Hour.getPerformanceAverageValue());
        businessView.setStatusAvailability1hour(availabilityAvg1Hour.getAvailabilityAverage().getStatus().toString());
        businessView.setStatusPerformance1hour(performanceAvg1Hour.getPerformanceAverage().getStatus().toString());
        businessView.setMeasuresQtyAvailability1hour(availabilityAvg1Hour.getMeasuresQtyAvailability().getQuantity());
        businessView.setMeasuresQtyPerformance1hour(performanceAvg1Hour.getMeasuresQtyPerformance().getQuantity());

        businessView.setAvailabilityCriticalValue1hour(availabilityAvg1Hour.getAvailabilityThreshold());
        businessView.setPerformanceCriticalValue1hour(performanceAvg1Hour.getCriticalThreshold());

        //1 Day
        ApplicationIndicators availabilityAvg1Day = availabilityService.calculateLastDayAverageByApplication(id);
        ApplicationIndicators performanceAvg1Day = performanceService.calculateLastDayAverageByApplication(id);

        businessView.setAvailabilityAverage1day(availabilityAvg1Day.getAvailabilityAverageValue());
        businessView.setPerformanceAverage1day(performanceAvg1Day.getPerformanceAverageValue());
        businessView.setStatusAvailability1day(availabilityAvg1Day.getAvailabilityAverage().getStatus().toString());
        businessView.setStatusPerformance1day(performanceAvg1Day.getPerformanceAverage().getStatus().toString());
        businessView.setMeasuresQtyPerformance1day(performanceAvg1Day.getMeasuresQtyPerformance().getQuantity());
        businessView.setMeasuresQtyAvailability1day(availabilityAvg1Day.getMeasuresQtyAvailability().getQuantity());

        businessView.setAvailabilityCriticalValue1day(availabilityAvg1Day.getAvailabilityThreshold());
        businessView.setPerformanceCriticalValue1day(performanceAvg1Day.getCriticalThreshold());

        //Issues 1 Day
        IssuesQuantity issues = issueService.calculateIssuesQuantityLastDay(id);
        businessView.setIssuesQtyLastDay(issues.getQuantity());
        businessView.setStatusIssuesQty(issues.getStatus().toString());
        businessView.setIssuesQtyCriticalThreshold(issues.getCriticalThreshold());

        //WorkItems
        WorkItemQuantity workItems = workItemService.calculateWorkItemQuantityLastDay(id);
        businessView.setWorkItems1day(workItems.getQuantity());
        businessView.setStatusWorkItemsQty(workItems.getStatus().toString());
        businessView.setWorkItemsQtyCriticalThreshold(workItems.getCriticalThreshold());

        //Uptime
        businessView.setUptime10min(uptimeService.calculateLast10MinutesUptime(id).get());
        businessView.setUptime1hour(uptimeService.calculateLastHourUptime(id).get());
        businessView.setUptime1day(uptimeService.calculateLastDayUptime(id).get());

        //Availability Kpi
        businessView.setAvailabilityLast10MinKpi(availabilityService.calculateLast10MinutesKpiByApplication(id).getAvailabilityIndicator().get());
        businessView.setAvailabilityLastHourKpi(availabilityService.calculateLastHourKpiByApplication(id).getAvailabilityIndicator().get());
        businessView.setAvailabilityLastDayKpi(availabilityService.calculateLastDayKpiByApplication(id).getAvailabilityIndicator().get());
        businessView.setAvailabilityLastMonthKpi(availabilityService.calculateLastMonthKpiByApplication(id).getAvailabilityIndicator().get());

        //Performance Kpi
        businessView.setAvailabilityLast10MinKpi(performanceService.calculateLast10MinutesKpiByApplication(id).getPerformanceIndicator().get());
        businessView.setAvailabilityLastHourKpi(performanceService.calculateLastHourKpiByApplication(id).getPerformanceIndicator().get());
        businessView.setAvailabilityLastDayKpi(performanceService.calculateLastDayKpiByApplication(id).getPerformanceIndicator().get());
        businessView.setAvailabilityLastMonthKpi(performanceService.calculateLastMonthKpiByApplication(id).getPerformanceIndicator().get());

        //Incidentes Kpi
        businessView.setIssuesKpiLastDay(issueService.calculateLastDayKpiByApplication(id).get());
        businessView.setIssuesKpiLastMonth(issueService.calculateLastMonthKpiByApplication(id).get());

        //TODO: Tareas Kpi, Eventos Kpi.
        return businessView;
    }

    @Override
    public List<BusinessApplication> getAllApplications() {
        List<Application> applicationDtoList = availabilityService.getAllApplications();
        return applicationMapper.mapToBusinessApplicationList(applicationDtoList);
    }

    @Override
    public List<ApplicationUptime> getUptimeLastMonth(String applicationId) {

        Calendar startCalendar = CalendarHelper.getNow();
        startCalendar.add(Calendar.MONTH, -1);
        Date startDate = startCalendar.getTime();

        Calendar endCalendar = CalendarHelper.getNow();
        endCalendar.add(Calendar.HOUR, 24);
        Date endDate = endCalendar.getTime();

        Uptime uptimeDto = uptimeService.getUptimeByApplication(applicationId, MonitoringFields.FRECUENCY.DAY, startDate, endDate);

        List<ApplicationUptime> uptimes = uptimeMapper.mapToUptimeList(uptimeDto);
        return uptimes;
    }

    @Override
    public List<Availability> getAvailabilityLast10Minutes(String applicationId) {
        List<Availability> availabilityList = availabilityService.getLast10Minutes(applicationId);
        availabilityList.sort(Comparator.comparing(Availability::getTimeStamp));
        return availabilityList;
    }

    @Override
    public List<Availability> getAvailabilityLastHour(String applicationId) {
        List<Availability> availabilityList = availabilityService.getLastHour(applicationId);
        availabilityList.sort(Comparator.comparing(Availability::getTimeStamp));
        return availabilityList;
    }
}
