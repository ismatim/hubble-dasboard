package hubble.backend.business.services.tests;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.implementations.operations.AvailabilityOperationImpl;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.core.utils.CalendarHelper;
import hubble.backend.storage.models.ApplicationStorage;
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
public class AvailabilityOperationUnitTests {

    @Mock
    private AvailabilityRepository availabilityRepository;
    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private AvailabilityOperationImpl availabilityOperation;

    @Spy
    private MapperConfiguration mapper;

    private List<AvailabilityStorage> availabilityStorageList;
    private AvailabilityHelper availabilityHelper = new AvailabilityHelper();

    @Before
    public void Before() {
        availabilityStorageList = new ArrayList();
    }

    @Test
    public void availability_service_should_be_instantiated() {
        //Assert
        assertNotNull(availabilityOperation);
    }

    @Test
    public void availability_service_should_calculate_last_10minutes_application_availability_average() {
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, applicationId)).thenReturn(availabilityStorageList);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = availabilityOperation.calculateLast10MinutesAverageByApplication(applicationId).getAvailabilityAverage().get();

        //Assert
        assertEquals(75, average);
    }

    @Test
    public void availability_service_should_calculate_last_day_application_availability_average() {
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_DAY, applicationId)).thenReturn(availabilityStorageList);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = availabilityOperation.calculateLastDayAverageByApplication(applicationId).getAvailabilityAverage().get();

        //Assert
        assertEquals(75, average);
    }

    @Test
    public void availability_service_should_return_null_when_average_calculation_encounters_no_data() {
        //Assign
        Integer average;
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, applicationId)).thenReturn(null);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = availabilityOperation.calculateLastHourAverageByApplication(applicationId).getAvailabilityAverage().get();

        //Assert
        assertEquals(null, average);
    }

    @Test
    public void availability_service_should_calculate_last_hour_application_availability_average() {
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_HOUR, applicationId)).thenReturn(availabilityStorageList);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = availabilityOperation.calculateLastHourAverageByApplication(applicationId).getAvailabilityAverage().get();

        //Assert
        assertEquals(75, average);
    }

    @Test
    public void availability_service_should_calculate_last_month_application_availability_average() {
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(CalendarHelper.ONE_MONTH, applicationId)).thenReturn(availabilityStorageList);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = availabilityOperation.calculateLastMonthAverageByApplication(applicationId).getAvailabilityAverage().get();

        //Assert
        assertEquals(75, average);
    }
}
