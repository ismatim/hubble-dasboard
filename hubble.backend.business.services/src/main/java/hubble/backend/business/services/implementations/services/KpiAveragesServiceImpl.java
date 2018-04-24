package hubble.backend.business.services.implementations.services;

import hubble.backend.business.services.interfaces.services.AvailabilityService;
import hubble.backend.business.services.interfaces.services.IssueService;
import hubble.backend.business.services.interfaces.services.PerformanceService;
import hubble.backend.business.services.interfaces.services.WorkItemService;
import hubble.backend.business.services.interfaces.services.kpis.KpiAveragesService;
import hubble.backend.business.services.models.business.ApplicationIndicators;
import hubble.backend.business.services.models.measures.kpis.IssuesKpi;
import hubble.backend.business.services.models.measures.kpis.WorkItemsKpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KpiAveragesServiceImpl implements KpiAveragesService {

    @Autowired
    PerformanceService performanceService;
    @Autowired
    AvailabilityService availabilityService;
    @Autowired
    WorkItemService workItemService;
    @Autowired
    IssueService issueService;

    @Override
    public Double getStandardHealthIndex(String applicationId) {

        ApplicationIndicators appPerformanceKpi = performanceService.calculateLast10MinutesKpiByApplication(applicationId);
        ApplicationIndicators appAvailabilityKpi = availabilityService.calculateLast10MinutesKpiByApplication(applicationId);

        IssuesKpi issuesKpi = issueService.calculateLastDayKpiByApplication(applicationId);
        WorkItemsKpi workItemsKpi = workItemService.calculateLastDayKpiByApplication(applicationId);

        double appAvalabilityValue = 0;
        double appPerformanceValue = 0;

        if (appAvailabilityKpi.getAvailabilityKpi() != null) {
            appAvalabilityValue = appAvailabilityKpi.getAvailabilityKpi().get();
        }

        if (appPerformanceKpi.getPerformanceKpi() != null) {
            appPerformanceValue = appPerformanceKpi.getPerformanceKpi().get();
        }

        Double standardAverage = appPerformanceValue
                + appAvalabilityValue
                + issuesKpi.get() + workItemsKpi.get();

        standardAverage = Double.valueOf(Math.round(standardAverage / 4));

        return standardAverage;
    }

}
