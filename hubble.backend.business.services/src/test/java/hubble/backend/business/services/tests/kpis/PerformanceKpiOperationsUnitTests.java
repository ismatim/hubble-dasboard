package hubble.backend.business.services.tests.kpis;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.implementations.operations.kpis.CalculateKpiLowNumberBestIndicatorImpl;
import hubble.backend.business.services.implementations.operations.kpis.PerformanceKpiOperationsImpl;
import hubble.backend.business.services.implementations.operations.rules.PerformanceRulesOperationsImpl;
import hubble.backend.business.services.models.measures.rules.PerformanceGroupRule;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import static org.junit.Assert.assertEquals;
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
public class PerformanceKpiOperationsUnitTests {

    @InjectMocks
    PerformanceKpiOperationsImpl performancepiOperations;
    @InjectMocks
    private PerformanceRulesOperationsImpl performanceRulesOperation;

    @Spy
    private MapperConfiguration mapper;
    @Spy
    private CalculateKpiLowNumberBestIndicatorImpl calculateKpi;

    @Test
    public void PerformanceKpiOperations_calculate_index_per_excellent_value() {

        //Assign
        PerformanceGroupRule performanceGroupRule = new PerformanceGroupRule();
        performanceGroupRule.set(100d);
        // Act
        double performanceIndex = performancepiOperations.calculateKeyPerformanceIndicator(performanceGroupRule);

        //Assert
        Mockito.verify(calculateKpi).calculateIndex();
        assertEquals(10, performanceIndex, 0.75d);
    }

    @Test
    public void AvailabilityKpiOperations_calculate_index_per_error_value() {

        //Assign
        PerformanceGroupRule performanceGroupRule = new PerformanceGroupRule();
        performanceGroupRule.set(12000d);
        // Act
        double performanceIndex = performancepiOperations.calculateKeyPerformanceIndicator(performanceGroupRule);

        //Assert
        Mockito.verify(calculateKpi).calculateIndex();
        assertEquals(4, performanceIndex, 0.8d);
    }

    @Test
    public void AvailabilityKpiOperations_calculate_index_per_critical_value() {

        //Assign
        PerformanceGroupRule performanceGroupRule = new PerformanceGroupRule();
        performanceGroupRule.set(20000d);
        // Act
        double performanceIndex = performancepiOperations.calculateKeyPerformanceIndicator(performanceGroupRule);

        //Assert
        Mockito.verify(calculateKpi).calculateIndex();
        assertEquals(0, performanceIndex, 0.8d);
    }

}
