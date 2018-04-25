package hubble.backend.business.services.tests.kpis;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.implementations.operations.kpis.CalculateKpiLowNumberBestIndicatorImpl;
import hubble.backend.business.services.implementations.operations.kpis.IssuesKpiOperationsImpl;
import hubble.backend.business.services.implementations.operations.rules.IssuesRulesOperationsImpl;
import hubble.backend.business.services.models.measures.rules.IssuesGroupRule;
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
public class IssuesKpiOperationsUnitTests {

    @InjectMocks
    IssuesKpiOperationsImpl issuesKpiOperations;
    @InjectMocks
    private IssuesRulesOperationsImpl issuesRulesOperation;

    @Spy
    private MapperConfiguration mapper;
    @Spy
    private CalculateKpiLowNumberBestIndicatorImpl calculateKpi;

    @Before
    public void setthreshold() {

        issuesKpiOperations.setWarningKpiThreshold(Threshold.Issues.WARNING_ISSUES_DAY_DEFAULT);
        issuesKpiOperations.setCriticalKpiThreshold(Threshold.Issues.CRITICAL_ISSUES_DAY_DEFAULT);
        issuesKpiOperations.setWarningIdxThreshold(KpiHelper.Issues.WARNING_ISSUES_KPI_DEFAULT);
        issuesKpiOperations.setCriticalIdxThreshold(KpiHelper.Issues.CRITICAL_ISSUES_KPI_DEFAULT);
    }

    @Test
    public void Spec_of_availabilityKpiOperations_when_calculate_index_per_cero() {

        //Assign
        IssuesGroupRule issuesGroupRule = new IssuesGroupRule();
        issuesGroupRule.set(0);
        // Act
        double issuesIndex = issuesKpiOperations.calculateKeyPerformanceIndicator(issuesGroupRule);

        //Assert
        assertEquals(10, issuesIndex, 0.1d);
    }

    @Test
    public void Spec_of_availabilityKpiOperations_when_calculate_index_per_max_value() {

        //Assign
        IssuesGroupRule issuesGroupRule = new IssuesGroupRule();
        issuesGroupRule.set(1);
        // Act
        double issuesIndex = issuesKpiOperations.calculateKeyPerformanceIndicator(issuesGroupRule);

        //Assert
        Mockito.verify(calculateKpi).calculateIndex();
        assertEquals(9.6, issuesIndex, 0.1d);
    }

    @Test
    public void Spec_of_availabilityKpiOperations_when_calculate_index_per_min_value() {

        //Assign
        IssuesGroupRule issuesGroupRule = new IssuesGroupRule();
        issuesGroupRule.set(200);
        // Act
        double issuesIndex = issuesKpiOperations.calculateKeyPerformanceIndicator(issuesGroupRule);

        //Assert
        Mockito.verify(calculateKpi).calculateIndex();
        assertEquals(0.3, issuesIndex, 0.8);
    }

    @Test
    public void Spec_of_availabilityKpiOperations_when_calculate_index_per_mid_value() {

        //Assign
        IssuesGroupRule issuesGroupRule = new IssuesGroupRule();
        issuesGroupRule.set(350);
        // Act
        double issuesIndex = issuesKpiOperations.calculateKeyPerformanceIndicator(issuesGroupRule);

        //Assert
        Mockito.verify(calculateKpi).calculateIndex();
        assertEquals(0.3, issuesIndex, 0.8);
    }
}
