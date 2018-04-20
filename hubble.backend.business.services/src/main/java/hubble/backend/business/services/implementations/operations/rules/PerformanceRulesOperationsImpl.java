/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hubble.backend.business.services.implementations.operations.rules;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.interfaces.operations.rules.PerformanceGroupRuleOperations;
import hubble.backend.business.services.interfaces.unitconverters.UnitConverter;
import hubble.backend.business.services.models.business.ApplicationIndicators;
import hubble.backend.business.services.models.measures.rules.PerformanceGroupRule;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import hubble.backend.storage.repositories.AvailabilityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ismaeltisminetzky
 */
@Component
public class PerformanceRulesOperationsImpl implements PerformanceGroupRuleOperations {

    @Autowired
    AvailabilityRepository availabilityRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    UnitConverter<ApplicationIndicators> unitConverter;
    @Autowired
    MapperConfiguration mapper;

    @Override
    public Double calculateGroupRule(List<AvailabilityStorage> availabilityStorageList) {
        int totalAvailabilities = availabilityStorageList.size();
        if (totalAvailabilities == 0) {
            Double.valueOf(0);
        }
        int averagePerformance = 0;
        for (AvailabilityStorage availability : availabilityStorageList) {
            averagePerformance += availability.getResponseTime();
        }

        return Double.valueOf(averagePerformance / totalAvailabilities);
    }

    @Override
    public PerformanceGroupRule calculateLast10MinutesGroupRuleByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, applicationId);

        ApplicationIndicators applicationIndicators = mapper.mapToApplicationIndicatorsDto(applicationStorage);

        if (applicationIndicators == null) {
            return null;
        }

        PerformanceGroupRule performanceRule = new PerformanceGroupRule();

        if (!availabilityStorageList.isEmpty()) {
            performanceRule.set(this.calculateGroupRule(availabilityStorageList));
        }

        performanceRule
                .setStatus(this.calculateGroupRuleStatus(applicationIndicators, performanceRule.get()));

        return performanceRule;
    }

    @Override
    public PerformanceGroupRule calculateLastHourGroupRuleByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_HOUR, applicationId);
        ApplicationIndicators applicationIndicators = mapper.mapToApplicationIndicatorsDto(applicationStorage);

        if (applicationIndicators == null) {
            return null;
        }

        PerformanceGroupRule performanceRule = new PerformanceGroupRule();

        if (!availabilityStorageList.isEmpty()) {
            performanceRule.set(this.calculateGroupRule(availabilityStorageList));
        }

        performanceRule
                .setStatus(this.calculateGroupRuleStatus(applicationIndicators, performanceRule.get()));

        return performanceRule;
    }

    @Override
    public PerformanceGroupRule calculateLastDayGroupRuleByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository
                .findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_DAY, applicationId);
        ApplicationIndicators applicationIndicators = mapper.mapToApplicationIndicatorsDto(applicationStorage);

        if (applicationIndicators == null) {
            return null;
        }

        PerformanceGroupRule performanceRule = new PerformanceGroupRule();

        if (!availabilityStorageList.isEmpty()) {
            performanceRule.set(this.calculateGroupRule(availabilityStorageList));
        }

        performanceRule
                .setStatus(this.calculateGroupRuleStatus(applicationIndicators, performanceRule.get()));

        return performanceRule;
    }

    @Override
    public PerformanceGroupRule calculateLastMonthGroupRuleByApplication(String applicationId) {
        ApplicationStorage applicationStorage = applicationRepository.findApplicationById(applicationId);
        List<AvailabilityStorage> availabilityStorageList = availabilityRepository
                .findAvailabilitiesByApplicationIdAndDurationMonths(CalendarHelper.ONE_MONTH, applicationId);
        ApplicationIndicators applicationIndicators = mapper.mapToApplicationIndicatorsDto(applicationStorage);

        if (applicationIndicators == null) {
            return null;
        }

        PerformanceGroupRule performanceRule = new PerformanceGroupRule();

        if (!availabilityStorageList.isEmpty()) {
            performanceRule.set(this.calculateGroupRule(availabilityStorageList));
        }

        performanceRule
                .setStatus(this.calculateGroupRuleStatus(applicationIndicators, performanceRule.get()));

        return performanceRule;
    }

    @Override
    public MonitoringFields.STATUS calculateGroupRuleStatus(ApplicationIndicators appIndicators, Double performanceRule) {

        if (performanceRule == null) {
            appIndicators.setCriticalThreshold(0d);
            return MonitoringFields.STATUS.NO_DATA;
        }

        Double criticalThresholdInSeconds = appIndicators.getCriticalThreshold();
        Double okThresholdInSeconds = appIndicators.getOkThreshold();

        if (performanceRule >= criticalThresholdInSeconds) {
            appIndicators.setCriticalThreshold(performanceRule);
            return MonitoringFields.STATUS.CRITICAL;
        } else if (performanceRule > okThresholdInSeconds && performanceRule < criticalThresholdInSeconds) {
            return MonitoringFields.STATUS.WARNING;
        } else if (performanceRule > 0 && performanceRule < okThresholdInSeconds) {
            return MonitoringFields.STATUS.SUCCESS;
        }
        return MonitoringFields.STATUS.NO_DATA;

    }

}
