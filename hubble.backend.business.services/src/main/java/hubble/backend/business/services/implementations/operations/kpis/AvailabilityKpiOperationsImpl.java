package hubble.backend.business.services.implementations.operations.kpis;

import hubble.backend.business.services.interfaces.operations.kpis.AvailabilityKpiOperations;
import hubble.backend.business.services.interfaces.operations.kpis.CalculateKpiHighNumberBestIndicator;
import hubble.backend.business.services.interfaces.operations.rules.AvailabilityGroupRuleOperations;
import hubble.backend.business.services.models.measures.kpis.AvailabilityKpi;
import hubble.backend.business.services.models.measures.rules.AvailabilityGroupRule;
import hubble.backend.core.enums.MonitoringFields;
import hubble.backend.core.utils.KpiHelper;
import hubble.backend.core.utils.Threshold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityKpiOperationsImpl implements AvailabilityKpiOperations {

    @Autowired
    CalculateKpiHighNumberBestIndicator calculateAvailabilityKpi;
    @Autowired
    AvailabilityGroupRuleOperations availabilityRulesOperation;

    @Override
    public double calculateKeyPerformanceIndicator(AvailabilityGroupRule availabilityGroupRule) {

        if (availabilityGroupRule.get().intValue() == 0) {
            return 0;
        }

        calculateAvailabilityKpi.setWarningKpiThreshold(Threshold.WARNING_AVAILABILITY_DEFAULT);
        calculateAvailabilityKpi.setCriticalKpiThreshold(Threshold.CRITICAL_AVAILABILITY_DEFAULT);
        calculateAvailabilityKpi.setWarningIdxThreshold(KpiHelper.Availability.WARNING_AVALABILITY_KPI_DEFAULT);
        calculateAvailabilityKpi.setCriticalIdxThreshold(KpiHelper.Availability.CRITICAL_AVALABILITY_KPI_DEFAULT);
        calculateAvailabilityKpi.setValue(availabilityGroupRule.get().intValue());

        double indexValue = calculateAvailabilityKpi.calculateIndex();
        return indexValue;
    }

    @Override
    public AvailabilityKpi calculateLast10MinutesKeyPerformanceIndicatorByApplication(String applicationId) {

        AvailabilityGroupRule availabilityLastHourRuleGroup = availabilityRulesOperation.calculateLast10MinutesGroupRuleByApplication(applicationId);

        double kpi10Min = calculateKeyPerformanceIndicator(availabilityLastHourRuleGroup);

        AvailabilityKpi availabilityKpi = new AvailabilityKpi();
        availabilityKpi.set(kpi10Min);

        availabilityKpi.setStatus(calculateKpiStatus(kpi10Min));
        return availabilityKpi;
    }

    @Override
    public AvailabilityKpi calculateLastHourKeyPerformanceIndicatorByApplication(String applicationId) {

        AvailabilityGroupRule availabilityLastHourRuleGroup = availabilityRulesOperation.calculateLastHourGroupRuleByApplication(applicationId);

        double kpiLastHour = calculateKeyPerformanceIndicator(availabilityLastHourRuleGroup);

        AvailabilityKpi availabilityKpi = new AvailabilityKpi();
        availabilityKpi.set(kpiLastHour);

        availabilityKpi.setStatus(calculateKpiStatus(kpiLastHour));
        return availabilityKpi;

    }

    @Override
    public AvailabilityKpi calculateLastDayKeyPerformanceIndicatorByApplication(String applicationId) {

        AvailabilityGroupRule availabilityLastDayRuleGroup = availabilityRulesOperation.calculateLastDayGroupRuleByApplication(applicationId);

        double kpiLastDay = calculateKeyPerformanceIndicator(availabilityLastDayRuleGroup);

        AvailabilityKpi availabilityKpi = new AvailabilityKpi();
        availabilityKpi.set(kpiLastDay);

        availabilityKpi.setStatus(calculateKpiStatus(kpiLastDay));
        return availabilityKpi;

    }

    @Override
    public AvailabilityKpi calculateLastMonthKeyPerformanceIndicatorByApplication(String applicationId) {
        AvailabilityGroupRule availabilityLastMonthRuleGroup = availabilityRulesOperation.calculateLastMonthGroupRuleByApplication(applicationId);

        double kpiLastMonth = calculateKeyPerformanceIndicator(availabilityLastMonthRuleGroup);

        AvailabilityKpi availabilityKpi = new AvailabilityKpi();
        availabilityKpi.set(kpiLastMonth);

        availabilityKpi.setStatus(calculateKpiStatus(kpiLastMonth));
        return availabilityKpi;
    }

    @Override
    public MonitoringFields.STATUS calculateKpiStatus(Double measure) {

        if (measure == 0) {
            return MonitoringFields.STATUS.NO_DATA;
        }

        //TODO: Determinará el Status del KPI de Disponibilidad.
        if (measure <= Threshold.WARNING_AVAILABILITY_DEFAULT) {
            return MonitoringFields.STATUS.SUCCESS;
        } else if (measure > Threshold.WARNING_AVAILABILITY_DEFAULT
                && measure < Threshold.CRITICAL_AVAILABILITY_DEFAULT) {
            return MonitoringFields.STATUS.WARNING;
        } else if (measure > Threshold.CRITICAL_AVAILABILITY_DEFAULT) {
            return MonitoringFields.STATUS.CRITICAL;
        }
        return MonitoringFields.STATUS.NO_DATA;
    }
}
