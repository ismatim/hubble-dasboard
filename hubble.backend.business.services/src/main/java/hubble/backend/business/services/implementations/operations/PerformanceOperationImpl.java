package hubble.backend.business.services.implementations.operations;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import hubble.backend.storage.repositories.AvailabilityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import hubble.backend.business.services.interfaces.operations.PerformanceOperations;

@Component
public class PerformanceOperationImpl implements PerformanceOperations {

    @Autowired
    AvailabilityRepository availabilityRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    MapperConfiguration mapper;

    @Override
    public int calculateAverage(List<AvailabilityStorage> availabilityStorageList) {
        int totalAvailabilities = availabilityStorageList.size();
        if (totalAvailabilities == 0) {
            return -1;
        }
        int averagePerformance = 0;
        for (AvailabilityStorage availability : availabilityStorageList) {
            averagePerformance += availability.getResponseTime();
        }

        return averagePerformance / totalAvailabilities;
    }

    @Override
    public MonitoringFields.STATUS calculateStatus(ApplicationDto appAvg, Integer avgPerformance) {

        if (avgPerformance == null) {
            appAvg.setCriticalThreshold(0);
            return MonitoringFields.STATUS.NO_DATA;
        }

        if (avgPerformance >= appAvg.getCriticalThreshold()) {
            return MonitoringFields.STATUS.CRITICAL;
        } else if (avgPerformance > appAvg.getOkThreshold() && avgPerformance < appAvg.getCriticalThreshold()) {
            return MonitoringFields.STATUS.WARNING;
        } else if (avgPerformance > 0 && avgPerformance < appAvg.getOkThreshold()) {
            return MonitoringFields.STATUS.SUCCESS;
        }

        return MonitoringFields.STATUS.NO_DATA;

    }

    @Override
    public ApplicationAvgDto calculateLast10MinutesAverageByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, applicationId);
        ApplicationAvgDto applicationPerformanceAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);

        applicationPerformanceAvg.getMeasuresQtyPerformance().setQuantity(availabilityStorageList.size());

        if (!availabilityStorageList.isEmpty()) {
            applicationPerformanceAvg.setPerformanceAverage(this.calculateAverage(availabilityStorageList));
        }

        applicationPerformanceAvg.getPerformanceAverage()
                .setStatus(this.calculateStatus(applicationPerformanceAvg, applicationPerformanceAvg.getPerformanceAverage().get()));

        return applicationPerformanceAvg;
    }

    @Override
    public ApplicationAvgDto calculateLastHourAverageByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_HOUR, applicationId);
        ApplicationAvgDto applicationPerformanceAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);

        applicationPerformanceAvg.getMeasuresQtyPerformance().setQuantity(availabilityStorageList.size());

        if (!availabilityStorageList.isEmpty()) {
            applicationPerformanceAvg.setPerformanceAverage(this.calculateAverage(availabilityStorageList));
        }
        applicationPerformanceAvg.getPerformanceAverage()
                .setStatus(this.calculateStatus(applicationPerformanceAvg, applicationPerformanceAvg.getPerformanceAverage().get()));

        return applicationPerformanceAvg;
    }

    @Override
    public ApplicationAvgDto calculateLastDayAverageByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_DAY, applicationId);
        ApplicationAvgDto applicationPerformanceAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);

        applicationPerformanceAvg.getMeasuresQtyPerformance().setQuantity(availabilityStorageList.size());

        if (!availabilityStorageList.isEmpty()) {
            applicationPerformanceAvg.setPerformanceAverage(this.calculateAverage(availabilityStorageList));
        }

        applicationPerformanceAvg
                .getPerformanceAverage()
                .setStatus(this.calculateStatus(applicationPerformanceAvg, applicationPerformanceAvg.getPerformanceAverage().get()));

        return applicationPerformanceAvg;
    }

    @Override
    public ApplicationAvgDto calculateLastMonthAverageByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(CalendarHelper.ONE_MONTH, applicationId);
        ApplicationAvgDto applicationPerformanceAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);

        if (!availabilityStorageList.isEmpty()) {//data for last month
            applicationPerformanceAvg.setPerformanceAverage(this.calculateAverage(availabilityStorageList));
        }

        applicationPerformanceAvg
                .getPerformanceAverage()
                .setStatus(this.calculateStatus(applicationPerformanceAvg, applicationPerformanceAvg.getPerformanceAverage().get()));

        return applicationPerformanceAvg;
    }
}
