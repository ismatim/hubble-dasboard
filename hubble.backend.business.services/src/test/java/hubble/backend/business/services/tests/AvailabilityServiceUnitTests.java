package hubble.backend.business.services.tests;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.implementations.operations.AvailabilityOperationImpl;
import hubble.backend.business.services.implementations.services.AvailabilityServiceImpl;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityDto;
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
public class AvailabilityServiceUnitTests {

    @Mock
    private AvailabilityRepository availabilityRepository;
    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @Spy
    private AvailabilityOperationImpl availabilityOperation;

    @Spy
    private MapperConfiguration mapper;

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
        availabilityService.getAll();

        //Assert
        verify(availabilityRepository).findAll();
    }

    @Test
    public void availability_service_should_return_all_availabilities() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();

        //Act
        when(availabilityRepository.findAll()).thenReturn(availabilityStorageList);

        //Assert
        assertEquals(4, availabilityService.getAll().size());
    }

    @Test
    public void availability_service_should_return_availability_by_id_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        AvailabilityDto availabilityDto;

        //Act
        AvailabilityStorage availabilityStorage = availabilityStorageList.get(0);
        when(availabilityRepository.findOne("1")).thenReturn(availabilityStorage);
        availabilityDto = availabilityService.get("1");

        //Assert
        assertEquals("h3y44h5sk58f8sdf48f", availabilityDto.getId());
    }

    @Test
    public void availability_service_should_return_availability_by_application_id_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationId("1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.getAll("1");

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_10minutes_availabilities_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMinutes(CalendarHelper.TEN_MINUTES)).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.getLast10Minutes();

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_hour_availabilities_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMinutes(CalendarHelper.ONE_HOUR)).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.getLastHour();

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_day_availabilities_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMinutes(CalendarHelper.ONE_DAY)).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.getLastDay();

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_month_availabilities_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMonths(CalendarHelper.ONE_MONTH)).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.getLastMonth();

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_10minutes_availabilities_by_applicationid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.TEN_MINUTES, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.getLast10Minutes("1");

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_hour_availabilities_by_applicationid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_HOUR, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.getLastHour("1");

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_day_availabilities_by_applicationid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(CalendarHelper.ONE_DAY, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.getLastDay("1");

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_month_availabilities_by_applicationid_with_correct_model() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(CalendarHelper.ONE_MONTH, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.getLastMonth("1");

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void service_should_return_application_by_id() {
        //Assign
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();
        String applicationId = "b566958ec4ff28028672780d15edcf56";

        //Act
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);

        //Assert
        assertEquals("BancoRipley - HomeBanking", availabilityService.getApplication(applicationId).getApplicationName());
    }

    @Test
    public void service_should_return_all_applications() {
        //Assign
        List<ApplicationStorage> applicationStorageList = new ArrayList();
        List<ApplicationDto> applicationDtoList = new ArrayList();

        //Act
        applicationStorageList.add(new AvailabilityHelper().mockApplicationStorage());
        when(applicationRepository.findAllApplications()).thenReturn(applicationStorageList);
        applicationDtoList = availabilityService.getAllApplications();

        //Assert
        assertEquals(1, applicationDtoList.size());
    }
}
