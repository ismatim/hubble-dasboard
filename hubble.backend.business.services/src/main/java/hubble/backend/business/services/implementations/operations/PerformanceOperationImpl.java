package hubble.backend.business.services.implementations.operations;

import hubble.backend.business.services.configurations.UnitConverter;
import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.interfaces.operations.PerformanceOperations;
import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.measures.Unit.MEASURES;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import hubble.backend.storage.repositories.AvailabilityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PerformanceOperationImpl implements PerformanceOperations {

    @Autowired
    AvailabilityRepository availabilityRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    UnitConverter<ApplicationAvgDto> unitConverter;
    @Autowired
    MapperConfiguration mapper;

    @Override
    public Float calculateAverage(List<AvailabilityStorage> availabilityStorageList) {
        int totalAvailabilities = availabilityStorageList.size();
        if (totalAvailabilities == 0) {
            return null;
        }
        int averagePerformance = 0;
        for (AvailabilityStorage availability : availabilityStorageList) {
            averagePerformance += availability.getResponseTime();
        }

        return Float.valueOf(averagePerformance / totalAvailabilities);
    }

    @Override
    public MonitoringFields.STATUS calculateStatus(ApplicationDto appAvg, Float avgPerformance) {

        if (avgPerformance == null) {
            appAvg.setCriticalThreshold(0f);
            return MonitoringFields.STATUS.NO_DATA;
        }

        Float criticalThresholdInSeconds = appAvg.getCriticalThreshold();
        Float okThresholdInSeconds = appAvg.getOkThreshold();

        if (avgPerformance >= criticalThresholdInSeconds) {
            appAvg.setCriticalThreshold(avgPerformance);
            return MonitoringFields.STATUS.CRITICAL;
        } else if (avgPerformance > okThresholdInSeconds && avgPerformance < criticalThresholdInSeconds) {
            return MonitoringFields.STATUS.WARNING;
        } else if (avgPerformance > 0 && avgPerformance < okThresholdInSeconds) {
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

        applicationPerformanceAvg = unitConverter.to(applicationPerformanceAvg, MEASURES.SECONDS);
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

        applicationPerformanceAvg = unitConverter.to(applicationPerformanceAvg, MEASURES.SECONDS);
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

        applicationPerformanceAvg = unitConverter.to(applicationPerformanceAvg, MEASURES.SECONDS);
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

        applicationPerformanceAvg = unitConverter.to(applicationPerformanceAvg, MEASURES.SECONDS);
        return applicationPerformanceAvg;
    }
}
