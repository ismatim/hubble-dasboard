package hubble.frontend.managers.tests;

import hubble.backend.business.services.interfaces.services.AvailabilityService;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.frontend.business.configurations.mappers.ApplicationAvgMapperConfiguration;
import hubble.frontend.business.configurations.mappers.ApplicationMapperConfiguration;
import hubble.frontend.business.configurations.mappers.AvailabilityMapperConfiguration;
import hubble.frontend.business.configurations.mappers.TransactionAvgMapperConfiguration;
import hubble.frontend.business.configurations.mappers.TransactionMapperConfiguration;
import hubble.frontend.business.implementations.AvailabilityManagerImpl;
import hubble.frontend.managers.tests.configurations.BaseConfigurationTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = BaseConfigurationTest.class)
public class AvailabilityManagerUnitTest {

    @Mock
    AvailabilityService availabilityService;
    @Spy
    AvailabilityMapperConfiguration availabilityMapper;
    @Spy
    ApplicationMapperConfiguration applicationMapper;
    @Spy
    TransactionMapperConfiguration transactionMapper;
    @Spy
    ApplicationAvgMapperConfiguration applicationAvgMapper;
    @Spy
    TransactionAvgMapperConfiguration transactionAvgMapper;

    @InjectMocks
    private AvailabilityManagerImpl availabilityManager;
    private List<AvailabilityDto> availabilityDtoList;
    private AvailabilityHelper availabilityHelper = new AvailabilityHelper();

    public AvailabilityManagerUnitTest() {
    }

    @Before
    public void setUp() {
        availabilityDtoList = new ArrayList();
    }

    @Test
    public void availability_manager_should_return_one_sample_by_its_id() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.get("1")).thenReturn(availabilityDtoList.get(0));

        availabilityManager.getAvailabilityById("1");
        verify(availabilityService).get("1");
    }

    @Test
    public void availability_manager_should_return_all_availabilitys() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.getAll()).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.getAllAvailabilities().size());
    }

    @Test
    public void availability_manager_should_return_all_availability_by_its_application_id() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.getAll("1")).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.getAvailabilitiesByApplicationId("1").size());
    }

    @Test
    public void availability_manager_should_return_last_10_minutes_availability() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.getLast10Minutes()).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.getLast10MinutesAvailabilities().size());
    }

    @Test
    public void availability_manager_should_return_last_hour_availability() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.getLastHour()).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.getLastHourAvailabilities().size());
    }

    @Test
    public void availability_manager_should_return_last_10_minutes_availability_by_application_id() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.getLast10Minutes("1")).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.getLast10MinutesAvailabilitiesByApplicationId("1").size());
    }

    @Test
    public void availability_manager_should_return_last_hour_availability_by_application_id() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.getLastHour("1")).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.getLastHourAvailabilitiesByApplicationId("1").size());
    }

    @Test
    public void availability_manager_should_return_bussines_application_by_id() {
        when(availabilityService.getApplication("1")).thenReturn(availabilityHelper.getApplicationDto());
        assertEquals("1", availabilityManager.getBusinessApplicationById("1").getId());
    }

    @Test
    public void availability_manager_should_return_all_applications() {
        when(availabilityService.getAllApplications()).thenReturn(availabilityHelper.getAllMockAplication());
        assertEquals(3, availabilityManager.getAllApplications().size());
    }

}
