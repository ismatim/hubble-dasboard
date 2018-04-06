package hubble.backend.business.services.tests;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.implementations.operations.averages.AvailabilityOperationImpl;
import hubble.backend.business.services.implementations.operations.averages.PerformanceOperationImpl;
import hubble.backend.business.services.implementations.services.TransactionServiceImpl;
import hubble.backend.business.services.models.Availability;
import hubble.backend.business.services.models.Performance;
import hubble.backend.business.services.models.Transaction;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.models.TransactionStorage;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ServiceBaseConfigurationTest.class)
public class TransactionServiceUnitTests {

    @Mock
    private AvailabilityRepository availabilityRepository;
    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @Spy
    private AvailabilityOperationImpl availabilityOperation;
    @Spy
    private PerformanceOperationImpl performanceOperation;

    @Spy
    private MapperConfiguration mapper;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private List<AvailabilityStorage> availabilityStorageList;
    private AvailabilityHelper availabilityHelper = new AvailabilityHelper();

    @Before
    public void Before() {
        availabilityStorageList = new ArrayList();
    }

    @Test
    public void availability_service_should_be_instantiated() {
        //Assert
        assertNotNull(transactionService);
    }

    @Test
    public void verify_repository_mock_usage() {
        //Assign
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);

        //Act
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        transactionService.findTransactionById(transactionId);

        //Assert
        verify(transactionRepository).findTransactionById(transactionId);
    }

    @Test
    public void service_should_return_transaction_by_id() {
        //Assign
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        String transactionId = "2eae220e082697be3a0646400e5b54fa";

        //Act
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);

        //Assert
        assertEquals("Auntenticacion Biometrica", transactionService.findTransactionById(transactionId).getTransactionName());
    }

    @Test
    public void service_should_retrieve_all_transactions_by_an_application_id() {
        //Assign
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        List<Transaction> transactionDtoList = new ArrayList();

        //Act
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        transactionDtoList = transactionService.findTransactionsByApplicationId(applicationId);

        //Assert
        assertEquals(2, transactionDtoList.size());
    }

    @Test
    public void availability_service_should_return_availability_by_transaction_id_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<Availability> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionId("1")).thenReturn(availabilityStorageList);
        availabilityDtoList = transactionService.findAvailabilitiesByTransactionId("1");

        //Assert
        assertEquals(12, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_10minutes_availabilities_by_transactionid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<Availability> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = transactionService.findLast10MinutesAvailabilitiesByTransactionId("1");

        //Assert
        assertEquals(12, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_hour_availabilities_by_transactionid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<Availability> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_HOUR, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = transactionService.findLastHourAvailabilitiesByTransactionId("1");

        //Assert
        assertEquals(12, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_calculate_last_10minutes_transaction_availability_average() {
        //Assign
        Double average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionId)).thenReturn(parentApplicationStorage);
        average = transactionService.calculateLast10MinutesAverageTransactionAvailability(transactionId).getAverage();

        //Assert
        assertEquals(58, average.intValue());
    }

    @Test
    public void availability_service_should_calculate_last_hour_transaction_availability_average() {
        //Assign
        Double average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_HOUR, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionId)).thenReturn(parentApplicationStorage);
        average = transactionService.calculateLastHourAverageTransactionAvailability(transactionId).getAverage();

        //Assert
        assertEquals(58, average.intValue());
    }

    @Test
    public void availability_service_should_return_last_day_availabilities_by_transactionid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<Availability> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_DAY, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = transactionService.findLastDayAvailabilitiesByTransactionId("1");

        //Assert
        assertEquals(12, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_Month_availabilities_by_transactionid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<Availability> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(CalendarHelper.ONE_MONTH, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = transactionService.findLastMonthAvailabilitiesByTransactionId("1");

        //Assert
        assertEquals(12, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_calculate_last_day_transaction_availability_average() {
        //Assign
        Double average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_DAY, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionId)).thenReturn(parentApplicationStorage);
        average = transactionService.calculateLastDayAverageTransactionAvailability(transactionId).getAverage();

        //Assert
        assertEquals(58, average.intValue());
    }

    @Test
    public void availability_service_should_calculate_last_month_transaction_availability_average() {
        //Assign
        Double average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(CalendarHelper.ONE_MONTH, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionId)).thenReturn(parentApplicationStorage);
        average = transactionService.calculateLastMonthAverageTransactionAvailability(transactionId).getAverage();

        //Assert
        assertEquals(58, average.intValue());
    }

    @Test
    public void performance_service_should_return_last_10minutes_performances_by_transactionid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<Performance> performanceDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = transactionService.findLast10MinutesPerformanceByTransactionId("1");

        //Assert
        assertEquals(12, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_hour_performance_by_transactionid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<Performance> performanceDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_HOUR, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = transactionService.findLastHourPerformanceByTransactionId("1");

        //Assert
        assertEquals(12, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_calculate_last_10minutes_transaction_performance_average() {
        //Assign
        Double average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionStorage.getTransactionId())).thenReturn(parentApplicationStorage);
        average = transactionService.calculateLast10MinutesAverageTransactionPerformance(transactionId).getAverage();

        //Assert
        assertEquals(1389, average.intValue());
    }

    @Test
    public void performance_service_should_calculate_last_hour_transaction_performance_average() {
        //Assign
        Double average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_HOUR, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionStorage.getTransactionId())).thenReturn(parentApplicationStorage);
        average = transactionService.calculateLastHourAverageTransactionPerformance(transactionId).getAverage();

        //Assert
        assertEquals(1389, average.intValue());
    }

    @Test
    public void performance_service_should_return_last_day_performances_by_transactionid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<Performance> performanceDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_DAY, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = transactionService.findLastDayPerformanceByTransactionId("1");

        //Assert
        assertEquals(12, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_month_performance_by_transactionid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<Performance> performanceDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(1, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = transactionService.findLastMonthPerformanceByTransactionId("1");

        //Assert
        assertEquals(12, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_calculate_last_day_transaction_performance_average() {
        //Assign
        Double average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(CalendarHelper.ONE_DAY, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionStorage.getTransactionId())).thenReturn(parentApplicationStorage);
        average = transactionService.calculateLastDayAverageTransactionPerformance(transactionId).getAverage();

        //Assert
        assertEquals(1389, average.intValue());
    }

    @Test
    public void performance_service_should_calculate_last_month_transaction_performance_average() {
        //Assign
        Double average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(CalendarHelper.ONE_MONTH, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionStorage.getTransactionId())).thenReturn(parentApplicationStorage);
        average = transactionService.calculateLastMonthAverageTransactionPerformance(transactionId).getAverage();

        //Assert
        assertEquals(1389, average.intValue());
    }

    @Test
    public void performance_service_should_return_performance_by_transaction_id_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<Performance> performanceDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionId("1")).thenReturn(availabilityStorageList);
        performanceDtoList = transactionService.findPerformanceByTransactionId("1");

        //Assert
        assertEquals(12, performanceDtoList.size());
    }
}
