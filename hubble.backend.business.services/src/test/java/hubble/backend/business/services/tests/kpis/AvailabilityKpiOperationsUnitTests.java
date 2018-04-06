package hubble.backend.business.services.tests.kpis;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.implementations.operations.kpis.AvailabilityKpiOperationsImpl;
import hubble.backend.business.services.implementations.operations.kpis.CalculateKpiHighNumberBestIndicatorImpl;
import hubble.backend.business.services.implementations.operations.rules.AvailabilityRulesOperationsImpl;
import hubble.backend.business.services.models.measures.rules.AvailabilityGroupRule;
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
public class AvailabilityKpiOperationsUnitTests {

    @InjectMocks
    AvailabilityKpiOperationsImpl availabilityKpiOperations;
    @InjectMocks
    private AvailabilityRulesOperationsImpl availabilityRulesOperation;

    @Spy
    private MapperConfiguration mapper;
    @Spy
    private CalculateKpiHighNumberBestIndicatorImpl calculateKpi;

    @Test
    public void AvailabilityKpiOperations_calculate_index_per_max_value() {

        //Assign
        AvailabilityGroupRule availabilityGroupRule = new AvailabilityGroupRule();
        availabilityGroupRule.set(100d);
        // Act
        double availabilityIndex = availabilityKpiOperations.calculateKeyPerformanceIndicator(availabilityGroupRule);

        //Assert
        Mockito.verify(calculateKpi).calculateIndex();
        assertEquals(8, availabilityIndex, 0.1d);
    }

    @Test
    public void AvailabilityKpiOperations_calculate_index_per_min_value() {

        //Assign
        AvailabilityGroupRule availabilityGroupRule = new AvailabilityGroupRule();
        availabilityGroupRule.set(10d);
        // Act
        double availabilityIndex = availabilityKpiOperations.calculateKeyPerformanceIndicator(availabilityGroupRule);

        //Assert
        Mockito.verify(calculateKpi).calculateIndex();
        assertEquals(0, availabilityIndex, 0.8d);
    }

}
