package hubble.backend.business.services.tests.kpis;

import hubble.backend.business.services.implementations.operations.kpis.CalculateKpiHighNumberBestIndicatorImpl;
import hubble.backend.business.services.implementations.operations.kpis.CalculateKpiLowNumberBestIndicatorImpl;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ServiceBaseConfigurationTest.class)
public class CalculateKpisUnitTests {

    @InjectMocks
    CalculateKpiHighNumberBestIndicatorImpl calculateAvailabilityKpi;
    @InjectMocks
    CalculateKpiLowNumberBestIndicatorImpl calculateIssuesKpi;

    @Test
    public void calculateAvailabilityKpi_indexes_check_max_and_min() {

        //Assign
        calculateAvailabilityKpi.setWarningKpiThreshold(90);
        calculateAvailabilityKpi.setCriticalKpiThreshold(80);
        calculateAvailabilityKpi.setWarningIdxThreshold(8);
        calculateAvailabilityKpi.setCriticalIdxThreshold(4);

        //Act
        calculateAvailabilityKpi.setValue(0);
        double minIndex = calculateAvailabilityKpi.calculateIndex();
        calculateAvailabilityKpi.setValue(100);
        double maxIndex = calculateAvailabilityKpi.calculateIndex();

        //Assert
        assertEquals(0, minIndex, 0.5);
        assertEquals(8, maxIndex, 0.5);
    }

    @Test
    public void CalculateKpiLowNumberBestIndicator_indexes_check_max_and_min() {

        //Assign
        calculateIssuesKpi.setWarningKpiThreshold(90);
        calculateIssuesKpi.setCriticalKpiThreshold(80);
        calculateIssuesKpi.setWarningIdxThreshold(8);
        calculateIssuesKpi.setCriticalIdxThreshold(4);

        //Act
        calculateIssuesKpi.setValue(0);
        double minIndex = calculateIssuesKpi.calculateIndex();
        calculateIssuesKpi.setValue(100);
        double maxIndex = calculateIssuesKpi.calculateIndex();

        //Assert
        assertEquals(10, minIndex, 0.5d);
        assertEquals(2, maxIndex, 0.5d);
    }
}
