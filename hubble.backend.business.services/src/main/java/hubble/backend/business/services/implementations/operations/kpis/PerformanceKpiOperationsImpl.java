package hubble.backend.business.services.implementations.operations.kpis;

import hubble.backend.business.services.interfaces.operations.kpis.CalculateKpiLowNumberBestIndicator;
import hubble.backend.business.services.interfaces.operations.kpis.PerformanceKpiOperations;
import hubble.backend.business.services.interfaces.operations.rules.PerformanceGroupRuleOperations;
import hubble.backend.business.services.models.measures.kpis.PerformanceKpi;
import hubble.backend.business.services.models.measures.rules.PerformanceGroupRule;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.core.utils.KpiHelper;
import hubble.backend.core.utils.Threshold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PerformanceKpiOperationsImpl implements PerformanceKpiOperations {

    @Autowired
    CalculateKpiLowNumberBestIndicator calculatePerformanceKpi;
    @Autowired
    PerformanceGroupRuleOperations performanceRulesOperation;

    @Override
    public double calculateKeyPerformanceIndicator(PerformanceGroupRule performanceGroupRule) {

        if (performanceGroupRule.get().intValue() == 0) {
            return 0d;
        }

        calculatePerformanceKpi.setWarningKpiThreshold(Threshold.WARNING_PERFORMANCE_DEFAULT);
        calculatePerformanceKpi.setCriticalKpiThreshold(Threshold.CRITICAL_PERFORMANCE_DEFAULT);
        calculatePerformanceKpi.setWarningIdxThreshold(KpiHelper.Performance.WARNING_PERFORMANCE_KPI_DEFAULT);
        calculatePerformanceKpi.setCriticalIdxThreshold(KpiHelper.Performance.CRITICAL_PERFORMANCE_KPI_DEFAULT);
        calculatePerformanceKpi.setValue(performanceGroupRule.get().intValue());

        return calculatePerformanceKpi.calculateIndex();
    }

    @Override
    public PerformanceKpi calculateLast10MinutesKeyPerformanceIndicatorByApplication(String applicationId) {

        PerformanceGroupRule availabilityLastHourRuleGroup = performanceRulesOperation.calculateLast10MinutesGroupRuleByApplication(applicationId);

        double kpi10Min = calculateKeyPerformanceIndicator(availabilityLastHourRuleGroup);

        PerformanceKpi performanceKpi = new PerformanceKpi();
        performanceKpi.set(kpi10Min);

        performanceKpi.setStatus(calculateKpiStatus(kpi10Min));
        return performanceKpi;
    }

    @Override
    public PerformanceKpi calculateLastHourKeyPerformanceIndicatorByApplication(String applicationId) {

        PerformanceGroupRule availabilityLastHourRuleGroup = performanceRulesOperation.calculateLastHourGroupRuleByApplication(applicationId);

        double kpiLastHour = calculateKeyPerformanceIndicator(availabilityLastHourRuleGroup);

        PerformanceKpi performanceKpi = new PerformanceKpi();
        performanceKpi.set(kpiLastHour);

        performanceKpi.setStatus(calculateKpiStatus(kpiLastHour));
        return performanceKpi;

    }

    @Override
    public PerformanceKpi calculateLastDayKeyPerformanceIndicatorByApplication(String applicationId) {

        PerformanceGroupRule availabilityLastDayRuleGroup = performanceRulesOperation.calculateLastDayGroupRuleByApplication(applicationId);

        double kpiLastDay = calculateKeyPerformanceIndicator(availabilityLastDayRuleGroup);

        PerformanceKpi performanceKpi = new PerformanceKpi();
        performanceKpi.set(kpiLastDay);

        performanceKpi.setStatus(calculateKpiStatus(kpiLastDay));
        return performanceKpi;

    }

    @Override
    public PerformanceKpi calculateLastMonthKeyPerformanceIndicatorByApplication(String applicationId) {
        PerformanceGroupRule availabilityLastMonthRuleGroup = performanceRulesOperation.calculateLastMonthGroupRuleByApplication(applicationId);

        double kpiLastMonth = calculateKeyPerformanceIndicator(availabilityLastMonthRuleGroup);

        PerformanceKpi performanceKpi = new PerformanceKpi();
        performanceKpi.set(kpiLastMonth);

        performanceKpi.setStatus(calculateKpiStatus(kpiLastMonth));
        return performanceKpi;
    }

    @Override
    public MonitoringFields.STATUS calculateKpiStatus(Double measure) {

        if (measure == 0) {
            return MonitoringFields.STATUS.NO_DATA;
        }

        if (measure <= Threshold.WARNING_PERFORMANCE_DEFAULT) {
            return MonitoringFields.STATUS.SUCCESS;
        } else if (measure > Threshold.WARNING_PERFORMANCE_DEFAULT
                && measure < Threshold.CRITICAL_PERFORMANCE_DEFAULT) {
            return MonitoringFields.STATUS.WARNING;
        } else if (measure > Threshold.CRITICAL_PERFORMANCE_DEFAULT) {
            return MonitoringFields.STATUS.CRITICAL;
        }
        return MonitoringFields.STATUS.NO_DATA;
    }
}
