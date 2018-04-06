package hubble.backend.business.services.implementations.operations.averages;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.interfaces.operations.averages.AvailabilityOperations;
import hubble.backend.business.services.models.Application;
import hubble.backend.business.services.models.business.ApplicationIndicators;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.core.utils.Threshold;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import hubble.backend.storage.repositories.AvailabilityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityOperationImpl implements AvailabilityOperations {

    @Autowired
    AvailabilityRepository availabilityRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    MapperConfiguration mapper;

    @Override
    public Double calculateAverage(List<AvailabilityStorage> availabilityStorageList) {
        int totalAvailabilities = availabilityStorageList.size();
        if (totalAvailabilities == 0) {
            return null;
        }
        int okAvailabilites = 0;
        for (AvailabilityStorage availability : availabilityStorageList) {
            if (availability.getAvailabilityStatus().equals(MonitoringFields.STATUS.SUCCESS.toString())) {
                okAvailabilites++;
            }
        }
        return Double.valueOf(100 * okAvailabilites / totalAvailabilities);
    }

    @Override
    public MonitoringFields.STATUS calculateStatus(Application appAvg, Double avgAvailability) {

        if (avgAvailability == null) {
            appAvg.setAvailabilityThreshold(0d);
            return MonitoringFields.STATUS.NO_DATA;
        } else if (appAvg.getAvailabilityThreshold() == 0) {
            appAvg.setAvailabilityThreshold(Double.valueOf(Threshold.TOP_AVAILABILITY_PERCENTAGE));
        }

        if (avgAvailability <= Threshold.CRITICAL_AVAILABILITY_DEFAULT) {
            return MonitoringFields.STATUS.CRITICAL;
        } else if (avgAvailability > Threshold.CRITICAL_AVAILABILITY_DEFAULT && avgAvailability <= Threshold.WARNING_AVAILABILITY_DEFAULT) {
            return MonitoringFields.STATUS.WARNING;
        } else if (avgAvailability > Threshold.WARNING_AVAILABILITY_DEFAULT) {
            return MonitoringFields.STATUS.SUCCESS;
        }

        return MonitoringFields.STATUS.NO_DATA;
    }

    @Override
    public ApplicationIndicators calculateLast10MinutesAverageByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, applicationId);
        ApplicationIndicators applicationAvailabilityAvg = mapper.mapToApplicationIndicatorsDto(applicationStorage);

        if (applicationAvailabilityAvg == null) {
            return null;
        }

        applicationAvailabilityAvg.setMeasuresQtyAvailability(availabilityStorageList.size());

        if (!availabilityStorageList.isEmpty()) {//No data for last 10 minutes
            applicationAvailabilityAvg.setAvailabilityAverage(this.calculateAverage(availabilityStorageList));
        }
        applicationAvailabilityAvg.getAvailabilityAverage()
                .setStatus(this.calculateStatus(applicationAvailabilityAvg, applicationAvailabilityAvg.getAvailabilityAverage().get()));

        return applicationAvailabilityAvg;
    }

    @Override
    public ApplicationIndicators calculateLastHourAverageByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_HOUR, applicationId);
        ApplicationIndicators applicationAvailabilityAvg = mapper.mapToApplicationIndicatorsDto(applicationStorage);

        if (applicationAvailabilityAvg == null) {
            return null;
        }

        applicationAvailabilityAvg.setMeasuresQtyAvailability(availabilityStorageList.size());

        if (!availabilityStorageList.isEmpty()) {//No data for last hour
            applicationAvailabilityAvg.setAvailabilityAverage(this.calculateAverage(availabilityStorageList));
        }
        applicationAvailabilityAvg.getAvailabilityAverage()
                .setStatus(this.calculateStatus(applicationAvailabilityAvg, applicationAvailabilityAvg.getAvailabilityAverage().get()));

        return applicationAvailabilityAvg;
    }

    @Override
    public ApplicationIndicators calculateLastDayAverageByApplication(String applicationId) {

        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_DAY, applicationId);
        ApplicationIndicators applicationAvailabilityAvg = mapper.mapToApplicationIndicatorsDto(applicationStorage);

        if (applicationAvailabilityAvg == null) {
            return null;
        }

        applicationAvailabilityAvg.setMeasuresQtyAvailability(availabilityStorageList.size());

        if (!availabilityStorageList.isEmpty()) {//No data for last hour
            applicationAvailabilityAvg.setAvailabilityAverage(this.calculateAverage(availabilityStorageList));
        }
        applicationAvailabilityAvg.getAvailabilityAverage()
                .setStatus(this.calculateStatus(applicationAvailabilityAvg, applicationAvailabilityAvg.getAvailabilityAverage().get()));

        return applicationAvailabilityAvg;

    }

    @Override
    public ApplicationIndicators calculateLastMonthAverageByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(CalendarHelper.ONE_MONTH, applicationId);
        ApplicationIndicators applicationAvailabilityAvg = mapper.mapToApplicationIndicatorsDto(applicationStorage);

        if (!availabilityStorageList.isEmpty()) {//No data for last hour
            applicationAvailabilityAvg.setAvailabilityAverage(this.calculateAverage(availabilityStorageList));
        }

        applicationAvailabilityAvg.getAvailabilityAverage()
                .setStatus(
                        this.calculateStatus(applicationAvailabilityAvg,
                                applicationAvailabilityAvg.getAvailabilityAverage().get()));
        return applicationAvailabilityAvg;
    }

}
