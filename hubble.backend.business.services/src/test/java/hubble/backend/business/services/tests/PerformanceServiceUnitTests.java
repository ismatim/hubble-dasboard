package hubble.backend.business.services.tests;

import hubble.backend.business.services.configurations.mappers.DtoMapperConfiguration;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.business.services.implementations.PerformanceServiceImpl;
import hubble.backend.business.services.models.PerformanceDto;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.models.TransactionStorage;
import hubble.backend.storage.repositories.ApplicationRepository;
import hubble.backend.storage.repositories.AvailabilityRepository;
import hubble.backend.storage.repositories.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.Spy;

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
    private DtoMapperConfiguration mapper;

    @InjectMocks
    private PerformanceServiceImpl performanceService;

    private List<AvailabilityStorage> availabilityStorageList;
    private AvailabilityHelper availabilityHelper = new AvailabilityHelper();

    @Before
    public void Before(){
        availabilityStorageList = new ArrayList();
    }

    @Test
    public void performance_service_should_be_instantiated() {
        //Assert
        assertNotNull(performanceService);
    }
    
    @Test
    public void performance_service_should_return_performance_by_id_with_correct_model(){
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        PerformanceDto performanceDto;
        
        //Act
        AvailabilityStorage availabilityStorage = availabilityStorageList.get(0);
        when(availabilityRepository.findOne("1")).thenReturn(availabilityStorage);
        performanceDto = performanceService.findPerformanceById("1");
        
        //Assert
        assertEquals("h3y44h5sk58f8sdf48f", performanceDto.getId());
    }
    
    @Test
    public void performance_service_should_return_performance_by_application_id_with_correct_model(){
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;
        
        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationId("1")).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.findPerformanceByApplicationId("1");
        
        //Assert
        assertEquals(4, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_10minutes_performances_with_correct_model(){
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;
        
        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMinutes(10)).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.findLast10MinutesPerformances();
        
        //Assert
        assertEquals(4, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_hour_performances_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;
        
        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMinutes(60)).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.findLastHourPerformances();
        
        //Assert
        assertEquals(4, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_10minutes_performances_by_applicationid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;
        
        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(10, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.findLast10MinutesPerformanceByApplicationId("1");
        
        //Assert
        assertEquals(4, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_hour_performance_by_applicationid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;
        
        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(60, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.findLastHourPerformanceByApplicationId("1");
        
        //Assert
        assertEquals(4, performanceDtoList.size());
    }
        
    @Test
    public void performance_service_should_calculate_last_10minutes_application_performance_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();
        
        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(10, applicationId)).thenReturn(availabilityStorageList);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = performanceService.calculateLast10MinutesAverageApplicationPerformance(applicationId).getAverage();
        
        //Assert
        assertEquals(1226, average);
    }
    
    @Test
    public void performance_service_should_return_negative1_when_average_performance_calculation_encounters_no_data(){
        //Assign
        int average;
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();
        
        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(10, applicationId)).thenReturn(null);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = performanceService.calculateLastHourAverageApplicationPerformance(applicationId).getAverage();
        
        //Assert
        assertEquals(-1, average);
    }
    
    @Test
    public void performance_service_should_calculate_last_hour_application_performance_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();
        
        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(60, applicationId)).thenReturn(availabilityStorageList);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = performanceService.calculateLastHourAverageApplicationPerformance(applicationId).getAverage();
        
        //Assert
        assertEquals(1226, average);
    }
   
    @Test
    public void performance_service_should_return_performance_by_transaction_id_with_correct_model(){
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;
        
        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionId("1")).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.findPerformanceByTransactionId("1");
        
        //Assert
        assertEquals(4, performanceDtoList.size());
    }
    
    @Test
    public void performance_service_should_return_last_10minutes_performances_by_transactionid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;
        
        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(10, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.findLast10MinutesPerformanceByTransactionId("1");
        
        //Assert
        assertEquals(4, performanceDtoList.size());
    }
    
    @Test
    public void performance_service_should_return_last_hour_performance_by_transactionid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;
        
        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(60, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.findLastHourPerformanceByTransactionId("1");
        
        //Assert
        assertEquals(4, performanceDtoList.size());
    }
    
    @Test
    public void performance_service_should_calculate_last_10minutes_transaction_performance_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();
        
        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(10, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionStorage.getTransactionId())).thenReturn(parentApplicationStorage);
        average = performanceService.calculateLast10MinutesAverageTransactionPerformance(transactionId).getAverage();
        
        //Assert
        assertEquals(1226, average);
    }
    
    @Test
    public void performance_service_should_calculate_last_hour_transaction_performance_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();
        
        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(60, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionStorage.getTransactionId())).thenReturn(parentApplicationStorage);
        average = performanceService.calculateLastHourAverageTransactionPerformance(transactionId).getAverage();
        
        //Assert
        assertEquals(1226, average);
    }
    
    @Test
    public void performance_service_should_return_last_day_performances_with_correct_model(){
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;
        
        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMinutes(10)).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.findLast10MinutesPerformances();
        
        //Assert
        assertEquals(4, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_month_performances_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;
        
        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMonths(1)).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.findLastMonthPerformances();
        
        //Assert
        assertEquals(4, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_day_performances_by_applicationid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;
        
        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(1440, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.findLastDayPerformanceByApplicationId("1");
        
        //Assert
        assertEquals(4, performanceDtoList.size());
    }

    @Test
    public void performance_service_should_return_last_month_performance_by_applicationid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;
        
        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(1, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.findLastMonthPerformanceByApplicationId("1");
        
        //Assert
        assertEquals(4, performanceDtoList.size());
    }
        
    @Test
    public void performance_service_should_calculate_last_day_application_performance_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();
        
        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(1440, applicationId)).thenReturn(availabilityStorageList);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = performanceService.calculateLastDayAverageApplicationPerformance(applicationId).getAverage();
        
        //Assert
        assertEquals(1226, average);
    }
    
    @Test
    public void performance_service_should_return_negative1_when_average_last_month_performance_calculation_encounters_no_data(){
        //Assign
        int average;
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();
        
        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(1, applicationId)).thenReturn(null);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = performanceService.calculateLastMonthAverageApplicationPerformance(applicationId).getAverage();
        
        //Assert
        assertEquals(-1, average);
    }
    
    @Test
    public void performance_service_should_calculate_last_month_application_performance_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();
        
        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(1, applicationId)).thenReturn(availabilityStorageList);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = performanceService.calculateLastMonthAverageApplicationPerformance(applicationId).getAverage();
        
        //Assert
        assertEquals(1226, average);
    }
   
    @Test
    public void performance_service_should_return_last_day_performances_by_transactionid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;
        
        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(1440, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.findLastDayPerformanceByTransactionId("1");
        
        //Assert
        assertEquals(4, performanceDtoList.size());
    }
  
    @Test
    public void performance_service_should_return_last_month_performance_by_transactionid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<PerformanceDto> performanceDtoList;
        
        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(1, "1")).thenReturn(availabilityStorageList);
        performanceDtoList = performanceService.findLastMonthPerformanceByTransactionId("1");
        
        //Assert
        assertEquals(4, performanceDtoList.size());
    }
    
    @Test
    public void performance_service_should_calculate_last_day_transaction_performance_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();
        
        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(1440, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionStorage.getTransactionId())).thenReturn(parentApplicationStorage);
        average = performanceService.calculateLastDayAverageTransactionPerformance(transactionId).getAverage();
        
        //Assert
        assertEquals(1226, average);
    }
   
    @Test
    public void performance_service_should_calculate_last_month_transaction_performance_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();
        
        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(1, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionStorage.getTransactionId())).thenReturn(parentApplicationStorage);
        average = performanceService.calculateLastMonthAverageTransactionPerformance(transactionId).getAverage();
        
        //Assert
        assertEquals(1226, average);
    }
}
