package hubble.frontend.business.implementations;

import hubble.backend.business.services.interfaces.services.AvailabilityService;
import hubble.backend.business.services.interfaces.services.IssueService;
import hubble.backend.business.services.interfaces.services.PerformanceService;
import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.measures.IssuesQuantity;
import hubble.frontend.business.configurations.mappers.ApplicationMapper;
import hubble.frontend.business.interfaces.BusinessApplicationManager;
import hubble.frontend.business.models.BusinessApplication;
import hubble.frontend.business.views.application.BusinessApplicationView;
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

    @Override
    public BusinessApplicationView getBusinessApplicationView(String id) {

        BusinessApplicationView businessView = new BusinessApplicationView();

        //10 minuntes
        ApplicationAvgDto availabilityAvg10min = availabilityService.calculateLast10MinutesAverageByApplication(id);
        ApplicationAvgDto performanceAvg10min = performanceService.calculateLast10MinutesAverageByApplication(id);

        businessView.setAvailabilityAverage10min(availabilityAvg10min.getAvailabilityAverageValue());
        businessView.setPerformanceAverage10min(performanceAvg10min.getPerformanceAverageValue());
        businessView.setStatusAvailability10min(availabilityAvg10min.getAvailabilityAverage().getStatus().toString());
        businessView.setStatusPerformance10min(performanceAvg10min.getPerformanceAverage().getStatus().toString());
        businessView.setMeasuresQtyAvailability10min(availabilityAvg10min.getMeasuresQtyAvailability().getQuantity());
        businessView.setMeasuresQtyPerformance10min(performanceAvg10min.getMeasuresQtyPerformance().getQuantity());

        businessView.setAvailabilityCriticalValue10min(availabilityAvg10min.getAvailabilityThreshold());
        businessView.setPerformanceCriticalValue10min(performanceAvg10min.getCriticalThreshold());

        //1 Hour
        ApplicationAvgDto availabilityAvg1Hour = availabilityService.calculateLastHourAverageByApplication(id);
        ApplicationAvgDto performanceAvg1Hour = performanceService.calculateLastHourAverageByApplication(id);

        businessView.setAvailabilityAverage1hour(availabilityAvg1Hour.getAvailabilityAverageValue());
        businessView.setPerformanceAverage1hour(performanceAvg1Hour.getPerformanceAverageValue());
        businessView.setStatusAvailability1hour(availabilityAvg1Hour.getAvailabilityAverage().getStatus().toString());
        businessView.setStatusPerformance1hour(performanceAvg1Hour.getPerformanceAverage().getStatus().toString());
        businessView.setMeasuresQtyAvailability1hour(availabilityAvg1Hour.getMeasuresQtyAvailability().getQuantity());
        businessView.setMeasuresQtyPerformance1hour(performanceAvg1Hour.getMeasuresQtyPerformance().getQuantity());

        businessView.setAvailabilityCriticalValue1hour(availabilityAvg1Hour.getAvailabilityThreshold());
        businessView.setPerformanceCriticalValue1hour(performanceAvg1Hour.getCriticalThreshold());

        //1 Day
        ApplicationAvgDto availabilityAvg1Day = availabilityService.calculateLastDayAverageByApplication(id);
        ApplicationAvgDto performanceAvg1Day = performanceService.calculateLastDayAverageByApplication(id);

        businessView.setAvailabilityAverage1day(availabilityAvg1Day.getAvailabilityAverageValue());
        businessView.setPerformanceAverage1day(performanceAvg1Day.getPerformanceAverageValue());
        businessView.setStatusAvailability1day(availabilityAvg1Day.getAvailabilityAverage().getStatus().toString());
        businessView.setStatusPerformance1day(performanceAvg1Day.getPerformanceAverage().getStatus().toString());
        businessView.setMeasuresQtyPerformance1day(performanceAvg1Day.getMeasuresQtyPerformance().getQuantity());
        businessView.setMeasuresQtyAvailability1day(availabilityAvg1Day.getMeasuresQtyAvailability().getQuantity());

        businessView.setAvailabilityCriticalValue1day(availabilityAvg1Day.getAvailabilityThreshold());
        businessView.setPerformanceCriticalValue1day(performanceAvg1Day.getCriticalThreshold());

        //Issues 1 Day
        IssuesQuantity issues = issueService.calculateIssuesQuantityLastMonth(id);
        businessView.setIssuesQtyLastDay(issues.getQuantity());
        businessView.setStatusIssuesQty(issues.getStatus().toString());
        businessView.setIssuesQtyCriticalThreshold(issues.getCriticalThreshold());

        return businessView;
    }

    @Override
    public List<BusinessApplication> getAllApplications() {
        List<ApplicationDto> applicationDtoList = availabilityService.getAllApplications();
        return applicationMapper.mapToBusinessApplicationList(applicationDtoList);
    }
}
