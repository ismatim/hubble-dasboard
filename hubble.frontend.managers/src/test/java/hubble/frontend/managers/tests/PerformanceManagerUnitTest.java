package hubble.frontend.managers.tests;

import hubble.backend.business.services.interfaces.AvailabilityService;
import hubble.backend.business.services.interfaces.PerformanceService;
import hubble.backend.business.services.models.PerformanceDto;
import hubble.frontend.managers.configurations.mappers.ManagerApplicationMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.ManagerPerformanceMapperConfiguration;
import hubble.frontend.managers.configurations.mappers.ManagerTransactionMapperConfiguration;
import hubble.frontend.managers.implementations.PerformanceManagerImpl;
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
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import static org.mockito.Mockito.when;
import org.mockito.Spy;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = BaseConfigurationTest.class)
public class PerformanceManagerUnitTest {
    
    
    @Mock
    PerformanceService performanceService;
    @Spy
    ManagerPerformanceMapperConfiguration performanceMapper;
    @Spy
    ManagerApplicationMapperConfiguration applicationMapper;
    @Spy
    ManagerTransactionMapperConfiguration transactionMapper;
    @InjectMocks
    private PerformanceManagerImpl performanceManager;
    private List<PerformanceDto> performanceDtoList;
    private PerformanceHelper performanceHelper = new PerformanceHelper();

    
    public PerformanceManagerUnitTest() {
    }

    @Before
    public void setUp() {
        performanceDtoList = new ArrayList();
    }

    @Test
    public void performance_manager_should_return_one_sample_by_its_id() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findPerformanceById("1")).thenReturn(performanceDtoList.get(0));
        
        performanceManager.findPerformanceById("1");
        verify(performanceService).findPerformanceById("1");
    }

    @Test
    public void performance_manager_should_return_all_performances() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findAllPerformances()).thenReturn(performanceDtoList);
        assertEquals(3,performanceManager.findAllPerformances().size());
    }
    
    @Test
    public void performance_manager_should_return_all_performance_by_its_application_id() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findPerformanceByApplicationId("1")).thenReturn(performanceDtoList);
        assertEquals(3,performanceManager.findPerformanceByApplicationId("1").size());
    }
    @Test
    public void performance_manager_should_return_all_performance_by_its_transaction_id() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findPerformanceByTransactionId("1")).thenReturn(performanceDtoList);
        assertEquals(3,performanceManager.findPerformanceByTransactionId("1").size());
    }
    
    @Test
    public void performance_manager_should_return_last_10_minutes_performance() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findLast10MinutesPerformances()).thenReturn(performanceDtoList);
        assertEquals(3,performanceManager.findLast10MinutesPerformance().size());
    }
    @Test
    public void performance_manager_should_return_last_hour_performance() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findLastHourPerformances()).thenReturn(performanceDtoList);
        assertEquals(3,performanceManager.findLastHourPerformance().size());
    }
    
    @Test
    public void performance_manager_should_return_last_10_minutes_performance_by_application_id() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findLast10MinutesPerformanceByApplicationId("1")).thenReturn(performanceDtoList);
        assertEquals(3,performanceManager.findLast10MinutesPerformanceByApplicationId("1").size());
    }
    
    @Test
    public void performance_manager_should_return_last_10_minutes_performance_by_transaction_id() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findLast10MinutesPerformanceByTransactionId("1")).thenReturn(performanceDtoList);
        assertEquals(3,performanceManager.findLast10MinutesPerformanceByTransactionId("1").size());
    }
    
    @Test
    public void performance_manager_should_return_last_hour_performance_by_application_id() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findLastHourPerformanceByApplicationId("1")).thenReturn(performanceDtoList);
        assertEquals(3,performanceManager.findLastHourPerformanceByApplicationId("1").size());
    }
    
    @Test
    public void performance_manager_should_return_last_hour_performance_by_transaction_id() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findLastHourPerformanceByTransactionId("1")).thenReturn(performanceDtoList);
        assertEquals(3,performanceManager.findLastHourPerformanceByTransactionId("1").size());
    }
    
    @Test
    public void performance_manager_should_return_last_10_minutes_performance_average_by_application_id() {        
        when(performanceService.calculateLast10MinutesAverageApplicationPerformance("1")).thenReturn(performanceHelper.getPerformanceApplicationAvg());
        assertEquals(900f,performanceManager.findLast10MinutesAverageByApplication("1").getAverage(),0.00002f);
    }
    
    @Test
    public void performance_manager_should_return_last_hour_performance_average_by_application_id() {        
        when(performanceService.calculateLastHourAverageApplicationPerformance("1")).thenReturn(performanceHelper.getPerformanceApplicationAvg());
        assertEquals(900f,performanceManager.findLastHourAverageByApplication("1").getAverage(),0.00002f);
    }
    
    @Test
    public void performance_manager_should_return_last_10_minutes_performance_average_by_transaction_id() {        
        when(performanceService.calculateLast10MinutesAverageTransactionPerformance("1")).thenReturn(performanceHelper.getTransactionAvailabilityAvgDto());
        assertEquals(900f,performanceManager.findLast10MinutesAverageByTransaction("1").getAverage(),0.00002f);
    }
    
    @Test
    public void performance_manager_should_return_last_hour_performance_average_by_transaction_id() {        
        when(performanceService.calculateLastHourAverageTransactionPerformance("1")).thenReturn(performanceHelper.getTransactionAvailabilityAvgDto());
        assertEquals(900f,performanceManager.findLastHourAverageByTransaction("1").getAverage(),0.00002f);
    }
    
    @Test
    public void performance_manager_should_return_bussines_application_by_id() {        
        when(performanceService.findApplicationById("1")).thenReturn(performanceHelper.getApplicationDto());
        assertEquals("1",performanceManager.findBusinessApplicationById("1").getId());
    }
    
    @Test
    public void performance_manager_should_return_all_applications() {        
        when(performanceService.findAllApplications()).thenReturn(performanceHelper.getAllMockAplication());
        assertEquals(3,performanceManager.findAllApplications().size());
    }
    
    @Test
    public void performance_manager_should_return_transactions_by_id() {        
        when(performanceService.findTransactionById("h5l3a4kdd9j393k")).thenReturn(performanceHelper.getMockTransactionDto());
        assertEquals("h5l3a4kdd9j393k",performanceManager.findBusinessTransactionById("h5l3a4kdd9j393k").getId());
    }
   
    
    @Test
    public void performance_manager_should_return_transactions_by_application_id() {        
        when(performanceService.findTransactionsByApplicationId("1")).thenReturn(performanceHelper.getMockTransactionDtoList());
        assertEquals(3,performanceManager.findTransactionsByApplication("1").size());
    }
    
    @Test
    public void performance_manager_should_return_last_day_performance() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findLastDayPerformances()).thenReturn(performanceDtoList);
        assertEquals(3,performanceManager.findLastDayPerformances().size());
    }
    
    @Test
    public void performance_manager_should_return_last_month_performance() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findLastMonthPerformances()).thenReturn(performanceDtoList);
        assertEquals(3,performanceManager.findLastMonthPerformances().size());
    }
    
    @Test
    public void performance_manager_should_return_last_day_performance_by_application_id() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findLastDayPerformanceByApplicationId("1")).thenReturn(performanceDtoList);
        assertEquals(3,performanceManager.findLastDayPerformanceByApplicationId("1").size());
    }
    
    @Test
    public void performance_manager_should_return_last_day_performance_by_transaction_id() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findLastDayPerformanceByTransactionId("1")).thenReturn(performanceDtoList);
        assertEquals(3,performanceManager.findLastDayPerformanceByTransactionId("1").size());
    }
    
    @Test
    public void performance_manager_should_return_last_month_performance_by_application_id() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findLastMonthPerformanceByApplicationId("1")).thenReturn(performanceDtoList);
        assertEquals(3,performanceManager.findLastMonthPerformanceByApplicationId("1").size());
    }
    
    @Test
    public void performance_manager_should_return_last_month_performance_by_transaction_id() {
        performanceDtoList = performanceHelper.mockData();
        
        when(performanceService.findLastMonthPerformanceByTransactionId("1")).thenReturn(performanceDtoList);
        assertEquals(3,performanceManager.findLastMonthPerformanceByTransactionId("1").size());
    }
    
    @Test
    public void performance_manager_should_return_last_day_performance_average_by_application_id() {        
        when(performanceService.calculateLastDayAverageApplicationPerformance("1")).thenReturn(performanceHelper.getPerformanceApplicationAvg());
        assertEquals(900f,performanceManager.findLastDayAverageByApplication("1").getAverage(),0.00002f);
    }
    
    @Test
    public void performance_manager_should_return_last_day_performance_average_by_transaction_id() {        
        when(performanceService.calculateLastDayAverageTransactionPerformance("1")).thenReturn(performanceHelper.getTransactionAvailabilityAvgDto());
        assertEquals(900f,performanceManager.findLastDayAverageByTransaction("1").getAverage(),0.00002f);
    }
    
    @Test
    public void performance_manager_should_return_last_month_performance_average_by_application_id() {        
        when(performanceService.calculateLastMonthAverageApplicationPerformance("1")).thenReturn(performanceHelper.getPerformanceApplicationAvg());
        assertEquals(900f,performanceManager.findLastMonthAverageByApplication("1").getAverage(),0.00002f);
    }
    
    @Test
    public void performance_manager_should_return_last_month_performance_average_by_transaction_id() {        
        when(performanceService.calculateLastMonthAverageTransactionPerformance("1")).thenReturn(performanceHelper.getTransactionAvailabilityAvgDto());
        assertEquals(900f,performanceManager.findLastMonthAverageByTransaction("1").getAverage(),0.00002f);
    }
}
