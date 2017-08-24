package hubble.frontend.managers.tests;

import hubble.backend.business.services.interfaces.AvailabilityService;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.frontend.managers.configurations.mappers.ApplicationMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.AvailabilityApplicationAvgMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.AvailabilityMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.AvailabilityTransactionAvgMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.TransactionMapperConfiguration;
import hubble.frontend.managers.implementations.AvailabilityManagerImpl;
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
    AvailabilityApplicationAvgMapperConfiguration applicationAvgMapper;
    @Spy
    AvailabilityTransactionAvgMapperConfiguration transactionAvgMapper;

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

        when(availabilityService.findAvailabilityById("1")).thenReturn(availabilityDtoList.get(0));

        availabilityManager.findAvailabilityById("1");
        verify(availabilityService).findAvailabilityById("1");
    }

    @Test
    public void availability_manager_should_return_all_availabilitys() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.findAllAvailabilities()).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.findAllAvailabilities().size());
    }

    @Test
    public void availability_manager_should_return_all_availability_by_its_application_id() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.findAvailabilitiesByApplicationId("1")).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.findAvailabilitiesByApplicationId("1").size());
    }

    @Test
    public void availability_manager_should_return_all_availability_by_its_transaction_id() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.findAvailabilitiesByTransactionId("1")).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.findAvailabilitiesByTransactionId("1").size());
    }

    @Test
    public void availability_manager_should_return_last_10_minutes_availability() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.findLast10MinutesAvailabilities()).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.findLast10MinutesAvailabilities().size());
    }

    @Test
    public void availability_manager_should_return_last_hour_availability() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.findLastHourAvailabilities()).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.findLastHourAvailabilities().size());
    }

    @Test
    public void availability_manager_should_return_last_10_minutes_availability_by_application_id() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.findLast10MinutesAvailabilitiesByApplicationId("1")).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.findLast10MinutesAvailabilitiesByApplicationId("1").size());
    }

    @Test
    public void availability_manager_should_return_last_10_minutes_availability_by_transaction_id() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.findLast10MinutesAvailabilitiesByTransactionId("1")).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.findLast10MinutesAvailabilitiesByTransactionId("1").size());
    }

    @Test
    public void availability_manager_should_return_last_hour_availability_by_application_id() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.findLastHourAvailabilitiesByApplicationId("1")).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.findLastHourAvailabilitiesByApplicationId("1").size());
    }

    @Test
    public void availability_manager_should_return_last_hour_availability_by_transaction_id() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.findLastHourAvailabilitiesByTransactionId("1")).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.findLastHourAvailabilitiesByTransactionId("1").size());
    }

    @Test
    public void availability_manager_should_return_last_10_minutes_availability_average_by_application_id() {
        when(availabilityService.calculateLast10MinutesAverageApplicationAvailability("1")).thenReturn(availabilityHelper.getAvailabilityApplicationAvg());
        assertEquals(900f, availabilityManager.findLast10MinutesAverageByApplication("1").getAverage(), 0.00002f);
    }

    @Test
    public void availability_manager_should_return_last_hour_availability_average_by_application_id() {
        when(availabilityService.calculateLastHourAverageApplicationAvailability("1")).thenReturn(availabilityHelper.getAvailabilityApplicationAvg());
        assertEquals(900f, availabilityManager.findLastHourAverageByApplication("1").getAverage(), 0.00002f);
    }

    @Test
    public void availability_manager_should_return_last_10_minutes_availability_average_by_transaction_id() {
        when(availabilityService.calculateLast10MinutesAverageTransactionAvailability("1")).thenReturn(availabilityHelper.getTransactionAvailabilityAvgDto());
        assertEquals(900f, availabilityManager.findLast10MinutesAverageByTransaction("1").getAverage(), 0.00002f);
    }

    @Test
    public void availability_manager_should_return_last_hour_availability_average_by_transaction_id() {
        when(availabilityService.calculateLastHourAverageTransactionAvailability("1")).thenReturn(availabilityHelper.getTransactionAvailabilityAvgDto());
        assertEquals(900f, availabilityManager.findLastHourAverageByTransaction("1").getAverage(), 0.00002f);
    }

    @Test
    public void availability_manager_should_return_bussines_application_by_id() {
        when(availabilityService.findApplicationById("1")).thenReturn(availabilityHelper.getApplicationDto());
        assertEquals("1", availabilityManager.findBusinessApplicationById("1").getId());
    }

    @Test
    public void availability_manager_should_return_all_applications() {
        when(availabilityService.findAllApplications()).thenReturn(availabilityHelper.getAllMockAplication());
        assertEquals(3, availabilityManager.findAllApplications().size());
    }

    @Test
    public void availability_manager_should_return_transactions_by_id() {
        when(availabilityService.findTransactionById("h5l3a4kdd9j393k")).thenReturn(availabilityHelper.getMockTransactionDto());
        assertEquals("h5l3a4kdd9j393k", availabilityManager.findBusinessTransactionById("h5l3a4kdd9j393k").getId());
    }

    @Test
    public void availability_manager_should_return_transactions_by_application_id() {
        when(availabilityService.findTransactionsByApplicationId("1")).thenReturn(availabilityHelper.getMockTransactionDtoList());
        assertEquals(3, availabilityManager.findTransactionsByApplication("1").size());
    }

    @Test
    public void availability_manager_should_return_last_day_availability() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.findLastDayAvailabilities()).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.findLastDayAvailabilities().size());
    }

    @Test
    public void availability_manager_should_return_last_month_availability() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.findLastMonthAvailabilities()).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.findLastMonthAvailabilities().size());
    }

    @Test
    public void availability_manager_should_return_last_day_availability_by_application_id() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.findLastDayAvailabilitiesByApplicationId("1")).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.findLastDayAvailabilitiesByApplicationId("1").size());
    }

    @Test
    public void availability_manager_should_return_last_day_availability_by_transaction_id() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.findLastDayAvailabilitiesByTransactionId("1")).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.findLastDayAvailabilitiesByTransactionId("1").size());
    }

    @Test
    public void availability_manager_should_return_last_month_availability_by_application_id() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.findLastMonthAvailabilitiesByApplicationId("1")).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.findLastMonthAvailabilitiesByApplicationId("1").size());
    }

    @Test
    public void availability_manager_should_return_last_month_availability_by_transaction_id() {
        availabilityDtoList = availabilityHelper.mockData();

        when(availabilityService.findLastMonthAvailabilitiesByTransactionId("1")).thenReturn(availabilityDtoList);
        assertEquals(3, availabilityManager.findLastMonthAvailabilitiesByTransactionId("1").size());
    }

    @Test
    public void availability_manager_should_return_last_day_availability_average_by_application_id() {
        when(availabilityService.calculateLastDayAverageApplicationAvailability("1")).thenReturn(availabilityHelper.getAvailabilityApplicationAvg());
        assertEquals(900f, availabilityManager.findLastDayAverageByApplication("1").getAverage(), 0.00002f);
    }

    @Test
    public void availability_manager_should_return_last_day_availability_average_by_transaction_id() {
        when(availabilityService.calculateLastDayAverageTransactionAvailability("1")).thenReturn(availabilityHelper.getTransactionAvailabilityAvgDto());
        assertEquals(900f, availabilityManager.findLastDayAverageByTransaction("1").getAverage(), 0.00002f);
    }

    @Test
    public void availability_manager_should_return_last_month_availability_average_by_application_id() {
        when(availabilityService.calculateLastMonthAverageApplicationAvailability("1")).thenReturn(availabilityHelper.getAvailabilityApplicationAvg());
        assertEquals(900f, availabilityManager.findLastMonthAverageByApplication("1").getAverage(), 0.00002f);
    }

    @Test
    public void availability_manager_should_return_last_month_availability_average_by_transaction_id() {
        when(availabilityService.calculateLastMonthAverageTransactionAvailability("1")).thenReturn(availabilityHelper.getTransactionAvailabilityAvgDto());
        assertEquals(900f, availabilityManager.findLastMonthAverageByTransaction("1").getAverage(), 0.00002f);
    }
}
