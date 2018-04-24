package hubble.backend.business.services.implementations.operations.rules;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.interfaces.operations.rules.AvailabilityGroupRuleOperations;
import hubble.backend.business.services.models.business.ApplicationIndicators;
import hubble.backend.business.services.models.measures.rules.AvailabilityGroupRule;
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
public class AvailabilityRulesOperationsImpl
        implements AvailabilityGroupRuleOperations {

    @Autowired
    AvailabilityRepository availabilityRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    MapperConfiguration mapper;

    @Override
    public Double calculateGroupRule(List<AvailabilityStorage> availabilityStorageList) {
        int totalAvailabilities = availabilityStorageList.size();
        if (totalAvailabilities == 0) {
            return Double.valueOf(0);
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
    public AvailabilityGroupRule calculateLast10MinutesGroupRuleByApplication(String applicationId) {

        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        AvailabilityGroupRule availabilityIndicator = new AvailabilityGroupRule();

        if (applicationStorage == null) {
            return availabilityIndicator;
        }

        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, applicationId);

        ApplicationIndicators applicationAvailabilityIndicators = mapper.mapToApplicationIndicatorsDto(applicationStorage);

        availabilityIndicator.set(calculateGroupRule(availabilityStorageList));

        availabilityIndicator.setStatus(calculateGroupRuleStatus(applicationAvailabilityIndicators, availabilityIndicator.get()));

        return availabilityIndicator;
    }

    @Override
    public AvailabilityGroupRule calculateLastHourGroupRuleByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_HOUR, applicationId);

        AvailabilityGroupRule availabilityIndicator = new AvailabilityGroupRule();

        if (applicationStorage == null) {
            return availabilityIndicator;
        }

        ApplicationIndicators applicationAvailabilityIndicators = mapper.mapToApplicationIndicatorsDto(applicationStorage);

        availabilityIndicator.set(calculateGroupRule(availabilityStorageList));
        availabilityIndicator.setStatus(calculateGroupRuleStatus(applicationAvailabilityIndicators, availabilityIndicator.get()));
        return availabilityIndicator;
    }

    @Override
    public AvailabilityGroupRule calculateLastDayGroupRuleByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_DAY, applicationId);

        AvailabilityGroupRule availabilityIndicator = new AvailabilityGroupRule();

        if (applicationStorage == null) {
            return availabilityIndicator;
        }

        ApplicationIndicators applicationAvailabilityIndicators = mapper.mapToApplicationIndicatorsDto(applicationStorage);

        availabilityIndicator.set(calculateGroupRule(availabilityStorageList));
        availabilityIndicator.setStatus(calculateGroupRuleStatus(applicationAvailabilityIndicators, availabilityIndicator.get()));
        return availabilityIndicator;
    }

    @Override
    public AvailabilityGroupRule calculateLastMonthGroupRuleByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(CalendarHelper.ONE_MONTH, applicationId);

        AvailabilityGroupRule availabilityIndicator = new AvailabilityGroupRule();

        if (applicationStorage == null) {
            return availabilityIndicator;
        }
        ApplicationIndicators applicationAvailabilityIndicators = mapper.mapToApplicationIndicatorsDto(applicationStorage);

        availabilityIndicator.set(calculateGroupRule(availabilityStorageList));
        availabilityIndicator.setStatus(calculateGroupRuleStatus(applicationAvailabilityIndicators, availabilityIndicator.get()));

        return availabilityIndicator;
    }

    @Override
    public MonitoringFields.STATUS calculateGroupRuleStatus(ApplicationIndicators appIndicator, Double kpiAvailability) {

        if (kpiAvailability == null) {
            appIndicator.setAvailabilityThreshold(0d);
            return MonitoringFields.STATUS.NO_DATA;
        } else if (appIndicator.getAvailabilityThreshold() == 0) {
            appIndicator.setAvailabilityThreshold(Double.valueOf(Threshold.TOP_AVAILABILITY_PERCENTAGE));
        }

        if (kpiAvailability <= Threshold.CRITICAL_AVAILABILITY_DEFAULT) {
            return MonitoringFields.STATUS.CRITICAL;
        } else if (kpiAvailability > Threshold.CRITICAL_AVAILABILITY_DEFAULT && kpiAvailability <= Threshold.WARNING_AVAILABILITY_DEFAULT) {
            return MonitoringFields.STATUS.WARNING;
        } else if (kpiAvailability > Threshold.WARNING_AVAILABILITY_DEFAULT) {
            return MonitoringFields.STATUS.SUCCESS;
        }

        return MonitoringFields.STATUS.NO_DATA;
    }

}
