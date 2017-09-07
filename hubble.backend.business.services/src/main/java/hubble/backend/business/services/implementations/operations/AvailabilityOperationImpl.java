package hubble.backend.business.services.implementations.operations;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.interfaces.operations.AvailabilityOperations;
import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.ApplicationDto;
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
    public Float calculateAverage(List<AvailabilityStorage> availabilityStorageList) {
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
        return Float.valueOf(100 * okAvailabilites / totalAvailabilities);
    }

    @Override
    public MonitoringFields.STATUS calculateStatus(ApplicationDto appAvg, Float avgAvailability) {

        if (avgAvailability == null) {
            appAvg.setAvailabilityThreshold(0f);
            return MonitoringFields.STATUS.NO_DATA;
        } else if (appAvg.getAvailabilityThreshold() == 0) {
            appAvg.setAvailabilityThreshold(Float.valueOf(Threshold.TOP_AVAILABILITY_PERCENTAGE));
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
    public ApplicationAvgDto calculateLast10MinutesAverageByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, applicationId);
        ApplicationAvgDto applicationAvailabilityAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);

        applicationAvailabilityAvg.setMeasuresQtyAvailability(availabilityStorageList.size());

        if (!availabilityStorageList.isEmpty()) {//No data for last 10 minutes
            applicationAvailabilityAvg.setAvailabilityAverage(this.calculateAverage(availabilityStorageList));
        }
        applicationAvailabilityAvg.getAvailabilityAverage()
                .setStatus(this.calculateStatus(applicationAvailabilityAvg, applicationAvailabilityAvg.getAvailabilityAverage().get()));

        return applicationAvailabilityAvg;
    }

    @Override
    public ApplicationAvgDto calculateLastHourAverageByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_HOUR, applicationId);
        ApplicationAvgDto applicationAvailabilityAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);

        applicationAvailabilityAvg.setMeasuresQtyAvailability(availabilityStorageList.size());

        if (!availabilityStorageList.isEmpty()) {//No data for last hour
            applicationAvailabilityAvg.setAvailabilityAverage(this.calculateAverage(availabilityStorageList));
        }
        applicationAvailabilityAvg.getAvailabilityAverage()
                .setStatus(this.calculateStatus(applicationAvailabilityAvg, applicationAvailabilityAvg.getAvailabilityAverage().get()));

        return applicationAvailabilityAvg;
    }

    @Override
    public ApplicationAvgDto calculateLastDayAverageByApplication(String applicationId) {

        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_DAY, applicationId);
        ApplicationAvgDto applicationAvailabilityAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);

        applicationAvailabilityAvg.setMeasuresQtyAvailability(availabilityStorageList.size());

        if (!availabilityStorageList.isEmpty()) {//No data for last hour
            applicationAvailabilityAvg.setAvailabilityAverage(this.calculateAverage(availabilityStorageList));
        }
        applicationAvailabilityAvg.getAvailabilityAverage()
                .setStatus(this.calculateStatus(applicationAvailabilityAvg, applicationAvailabilityAvg.getAvailabilityAverage().get()));

        return applicationAvailabilityAvg;

    }

    @Override
    public ApplicationAvgDto calculateLastMonthAverageByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(CalendarHelper.ONE_MONTH, applicationId);
        ApplicationAvgDto applicationAvailabilityAvg = mapper.mapToApplicationAvailabilityAvg(applicationStorage);

        if (!availabilityStorageList.isEmpty()) {//No data for last hour
            applicationAvailabilityAvg.setAvailabilityAverage(this.calculateAverage(availabilityStorageList));
        }

        applicationAvailabilityAvg.getAvailabilityAverage()
                .setStatus(
                        this
                                .calculateStatus(applicationAvailabilityAvg,
                                        applicationAvailabilityAvg.getAvailabilityAverage().get()));
        return applicationAvailabilityAvg;
    }
}
