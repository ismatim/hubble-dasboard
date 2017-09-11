package hubble.frontend.business.tests;

import hubble.backend.business.services.interfaces.services.PerformanceService;
import hubble.backend.business.services.models.PerformanceDto;
import hubble.frontend.business.configurations.mappers.ApplicationMapperConfiguration;
import hubble.frontend.business.configurations.mappers.PerformanceMapper;
import hubble.frontend.business.configurations.mappers.TransactionMapperConfiguration;
import hubble.frontend.business.implementations.PerformanceManagerImpl;
import hubble.frontend.business.tests.configurations.BaseConfigurationTest;
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
public class PerformanceManagerUnitTest {

    @Mock
    PerformanceService performanceService;
    @Spy
    PerformanceMapper performanceMapper;
    @Spy
    ApplicationMapperConfiguration applicationMapper;
    @Spy
    TransactionMapperConfiguration transactionMapper;
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

        when(performanceService.getById("1")).thenReturn(performanceDtoList.get(0));

        performanceManager.getPerformanceById("1");
        verify(performanceService).getById("1");
    }

    @Test
    public void performance_manager_should_return_all_performances() {
        performanceDtoList = performanceHelper.mockData();

        when(performanceService.getAll()).thenReturn(performanceDtoList);
        assertEquals(3, performanceManager.getAllPerformances().size());
    }

    @Test
    public void performance_manager_should_return_all_performance_by_its_application_id() {
        performanceDtoList = performanceHelper.mockData();

        when(performanceService.getAll("1")).thenReturn(performanceDtoList);
        assertEquals(3, performanceManager.getPerformanceByApplicationId("1").size());
    }

    @Test
    public void performance_manager_should_return_last_10_minutes_performance() {
        performanceDtoList = performanceHelper.mockData();

        when(performanceService.getLast10Minutes()).thenReturn(performanceDtoList);
        assertEquals(3, performanceManager.getLast10MinutesPerformance().size());
    }

    @Test
    public void performance_manager_should_return_last_hour_performance() {
        performanceDtoList = performanceHelper.mockData();

        when(performanceService.getLastHour()).thenReturn(performanceDtoList);
        assertEquals(3, performanceManager.getLastHourPerformance().size());
    }

    @Test
    public void performance_manager_should_return_last_10_minutes_performance_by_application_id() {
        performanceDtoList = performanceHelper.mockData();

        when(performanceService.getLast10Minutes("1")).thenReturn(performanceDtoList);
        assertEquals(3, performanceManager.getLast10MinutesPerformanceByApplicationId("1").size());
    }

    @Test
    public void performance_manager_should_return_last_hour_performance_by_application_id() {
        performanceDtoList = performanceHelper.mockData();

        when(performanceService.getLastHour("1")).thenReturn(performanceDtoList);
        assertEquals(3, performanceManager.getLastHourPerformanceByApplicationId("1").size());
    }

    @Test
    public void performance_manager_should_return_last_day_performance() {
        performanceDtoList = performanceHelper.mockData();

        when(performanceService.getLastDay()).thenReturn(performanceDtoList);
        assertEquals(3, performanceManager.getLastDayPerformances().size());
    }

    @Test
    public void performance_manager_should_return_last_month_performance() {
        performanceDtoList = performanceHelper.mockData();

        when(performanceService.getLastMonth()).thenReturn(performanceDtoList);
        assertEquals(3, performanceManager.getLastMonthPerformances().size());
    }

    @Test
    public void performance_manager_should_return_last_day_performance_by_application_id() {
        performanceDtoList = performanceHelper.mockData();

        when(performanceService.getLastDay("1")).thenReturn(performanceDtoList);
        assertEquals(3, performanceManager.getLastDayPerformanceByApplicationId("1").size());
    }

    @Test
    public void performance_manager_should_return_last_month_performance_by_application_id() {
        performanceDtoList = performanceHelper.mockData();

        when(performanceService.getLastMonth("1")).thenReturn(performanceDtoList);
        assertEquals(3, performanceManager.getLastMonthPerformanceByApplicationId("1").size());
    }

}
