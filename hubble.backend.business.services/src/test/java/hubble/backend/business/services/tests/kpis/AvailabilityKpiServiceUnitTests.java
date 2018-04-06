package hubble.backend.business.services.tests.kpis;

import hubble.backend.business.services.implementations.operations.averages.AvailabilityOperationImpl;
import hubble.backend.business.services.implementations.operations.kpis.AvailabilityKpiOperationsImpl;
import hubble.backend.business.services.implementations.services.AvailabilityServiceImpl;
import hubble.backend.business.services.models.business.ApplicationIndicators;
import hubble.backend.business.services.models.measures.kpis.AvailabilityKpi;
import hubble.backend.business.services.tests.AvailabilityHelper;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.ApplicationRepositoryImpl;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ServiceBaseConfigurationTest.class)
public class AvailabilityKpiServiceUnitTests {

    @Mock
    private ApplicationRepositoryImpl applicationRepository;
    @Mock
    private AvailabilityOperationImpl availabilityOperation;
    @Mock
    AvailabilityKpiOperationsImpl availavilityKpiOperation;

    @InjectMocks
    private AvailabilityServiceImpl availabilityService;

    private List<AvailabilityStorage> availabilityStorageList;
    private AvailabilityHelper availabilityHelper = new AvailabilityHelper();

    @Before
    public void Before() {
        availabilityStorageList = new ArrayList();
    }

    @Test
    public void availability_service_should_be_instantiated() {
        //Assert
        assertNotNull(availabilityService);
    }

    //KPI
    @Test
    public void availability_service_should_called_kpioperation_of_last_10_minutes() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        AvailabilityKpi availabilityKpiValue = new AvailabilityKpi();
        availabilityKpiValue.set(8);
        ApplicationIndicators availabilityDto;

        when(availavilityKpiOperation.calculateLast10MinutesKeyPerformanceIndicatorByApplication("1")).thenReturn(availabilityKpiValue);

        //Act
        availabilityDto = availabilityService.calculateLast10MinutesKpiByApplication("1");

        //Assert
        assertEquals(8, availabilityDto.getAvailabilityKpi().get(), 0.1d);
    }
}
