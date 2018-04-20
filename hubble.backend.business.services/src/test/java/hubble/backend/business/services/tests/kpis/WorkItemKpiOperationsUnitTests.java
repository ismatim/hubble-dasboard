package hubble.backend.business.services.tests.kpis;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.implementations.operations.kpis.CalculateKpiLowNumberBestIndicatorImpl;
import hubble.backend.business.services.implementations.operations.kpis.WorkItemKpiOperationsImpl;
import hubble.backend.business.services.implementations.operations.rules.WorkItemRulesOperationsImpl;
import hubble.backend.business.services.models.measures.rules.WorkItemsGroupRule;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.core.utils.KpiHelper;
import hubble.backend.core.utils.Threshold;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ServiceBaseConfigurationTest.class)
public class WorkItemKpiOperationsUnitTests {

    @InjectMocks
    WorkItemKpiOperationsImpl workItemKpiOperations;
    @InjectMocks
    private WorkItemRulesOperationsImpl workItemsRulesOperation;

    @Spy
    private MapperConfiguration mapper;
    @Spy
    private CalculateKpiLowNumberBestIndicatorImpl calculateKpi;

    @Before
    public void setthreshold() {

        workItemKpiOperations.setWarningKpiThreshold(Threshold.WorkItems.WARNING_WORKITEMS_MONTH_DEFAULT);
        workItemKpiOperations.setCriticalKpiThreshold(Threshold.WorkItems.CRITICAL_WORKITEMS_MONTH_DEFAULT);
        workItemKpiOperations.setWarningIdxThreshold(KpiHelper.WorkItems.WARNING_WORKITEMS_KPI_DEFAULT);
        workItemKpiOperations.setCriticalIdxThreshold(KpiHelper.WorkItems.CRITICAL_WORKITEMS_KPI_DEFAULT);
    }

    @Test
    public void Spec_of_workItemsKpiOperations_when_calculate_index_per_max_value_for_a_month() {

        //Assign
        WorkItemsGroupRule workItemsGroupRule = new WorkItemsGroupRule();
        workItemsGroupRule.set(4);

        // Act
        double workItemIndex = workItemKpiOperations.calculateKeyPerformanceIndicator(workItemsGroupRule);

        //Assert
        Mockito.verify(calculateKpi).calculateIndex();
        assertEquals(9.6, workItemIndex, 0.1d);
    }

    @Test
    public void Spec_of_workItemsKpiOperations_when_calculate_index_per_min_value_for_a_month() {

        //Assign
        WorkItemsGroupRule workItemsGroupRule = new WorkItemsGroupRule();
        workItemsGroupRule.set(33);
        // Act
        double workItemIndex = workItemKpiOperations.calculateKeyPerformanceIndicator(workItemsGroupRule);

        //Assert
        Mockito.verify(calculateKpi).calculateIndex();
        assertEquals(3.88, workItemIndex, 0.8);
    }

    @Test
    public void Spec_of_availabilityKpiOperations_when_calculate_index_per_mid_value_for_a_month() {

        //Assign
        WorkItemsGroupRule workItemsGroupRule = new WorkItemsGroupRule();
        workItemsGroupRule.set(10);
        // Act
        double workItemIndex = workItemKpiOperations.calculateKeyPerformanceIndicator(workItemsGroupRule);

        //Assert
        Mockito.verify(calculateKpi).calculateIndex();
        assertEquals(8.57, workItemIndex, 0.8);
    }
}
