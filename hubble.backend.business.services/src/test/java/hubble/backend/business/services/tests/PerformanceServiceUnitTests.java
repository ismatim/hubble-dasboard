package hubble.backend.business.services.tests;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.implementations.operations.PerformanceOperationImpl;
import hubble.backend.business.services.implementations.services.PerformanceServiceImpl;
import hubble.backend.business.services.implementations.services.TransactionServiceImpl;
import hubble.backend.business.services.models.PerformanceDto;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import hubble.backend.storage.repositories.AvailabilityRepository;
import hubble.backend.storage.repositories.TransactionRepository;
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
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ServiceBaseConfigurationTest.class)
public class PerformanceServiceUnitTests {

    @Mock
    private AvailabilityRepository availabilityRepository;
    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @Spy
    private MapperConfiguration mapper;
    @Spy
    private PerformanceOperationImpl performanceOperation;
    @Spy
    private TransactionServiceImpl transactinoService;

    @InjectMocks
    private PerformanceServiceImpl performanceService;

    private List<AvailabilityStorage> availabilityStorageList;
    private AvailabilityHelper availabilityHelper = new AvailabilityHelper();

    @Before
    public void Before() {
        availabilityStorageList = new ArrayList();
    }

    @Test
    public void performance_service_should_be_instantiated() {
        //Assert
        assertNotNull(performanceService);
    }

    @Test
    public void performance_service_should_return_performance_by_id_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        PerformanceDto performanceDto;

        //Act
        AvailabilityStorage availabilityStorage = availabilityStorageList.get(0);
        when(availabilityRepository.findOne("1")).thenReturn(availabilityStorage);
        performanceDto = performanceService.getById("1");

        //Assert
        assertEquals("h3y44h5sk58f8sdf48f", performanceDto.getId());
    }

    @Test
    public void performance_service_should_return_performance_by_application_id_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationId("1")).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.getAll("1");

        //Assert
        assertEquals(4, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_10minutes_performances_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMinutes(CalendarHelper.TEN_MINUTES)).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.getLast10Minutes();

        //Assert
        assertEquals(4, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_hour_performances_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMinutes(CalendarHelper.ONE_HOUR)).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.getLastHour();

        //Assert
        assertEquals(4, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_10minutes_performances_by_applicationid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.getLast10Minutes("1");

        //Assert
        assertEquals(4, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_hour_performance_by_applicationid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_HOUR, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.getLastHour("1");

        //Assert
        assertEquals(4, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_day_performances_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMinutes(CalendarHelper.TEN_MINUTES)).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.getLast10Minutes();

        //Assert
        assertEquals(4, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_month_performances_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMonths(1)).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.getLastMonth();

        //Assert
        assertEquals(4, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_day_performances_by_applicationid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_DAY, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.getLastDay("1");

        //Assert
        assertEquals(4, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_month_performance_by_applicationid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(CalendarHelper.ONE_MONTH, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.getLastMonth("1");

        //Assert
        assertEquals(4, performanceDtoList.size());
    }

}
