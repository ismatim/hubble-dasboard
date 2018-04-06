package hubble.backend.business.services.tests.integrations;

import hubble.backend.business.services.implementations.operations.kpis.AvailabilityKpiOperationsImpl;
import hubble.backend.business.services.models.measures.rules.AvailabilityGroupRule;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceBaseConfigurationTest.class)
public class AvailabilityKpiOperationsIntegrationTests {

    @Autowired
    AvailabilityKpiOperationsImpl availabilityKpiOperations;

    @Test
    public void AvailabilityKpiOperations_calculate_index_per_max_value() {

        //Assign
        AvailabilityGroupRule availabilityGroupRule = new AvailabilityGroupRule();
        availabilityGroupRule.set(100d);
        // Act
        double availabilityIndex = availabilityKpiOperations.calculateKeyPerformanceIndicator(availabilityGroupRule);

        //Assert
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
        assertEquals(0, availabilityIndex, 0.8d);
    }

}
