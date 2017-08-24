package hubble.backend.business.services.tests;

import hubble.backend.business.services.configurations.mappers.DtoMapperConfiguration;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.business.services.implementations.AvailabilityServiceImpl;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.backend.core.utils.HubbleConstants;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.Spy;

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
    private DtoMapperConfiguration mapper;

    @InjectMocks
    private AvailabilityServiceImpl availabilityService;

    private List<AvailabilityStorage> availabilityStorageList;
    private AvailabilityHelper availabilityHelper = new AvailabilityHelper();

    @Before
    public void Before(){
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
        availabilityService.findAllAvailabilities();
        availabilityService.calculateLast10MinutesAverageApplicationAvailability(applicationId);
        availabilityService.findTransactionById(transactionId);

        //Assert
        verify(availabilityRepository).findAll();
        verify(applicationRepository).findApplicationById(applicationId);
        verify(transactionRepository).findTransactionById(transactionId);
    }

    @Test
    public void availability_service_should_return_all_availabilities() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();

        //Act
        when(availabilityRepository.findAll()).thenReturn(availabilityStorageList);

        //Assert
        assertEquals(4,availabilityService.findAllAvailabilities().size());
    }

    @Test
    public void availability_service_should_return_availability_by_id_with_correct_model(){
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        AvailabilityDto availabilityDto;

        //Act
        AvailabilityStorage availabilityStorage = availabilityStorageList.get(0);
        when(availabilityRepository.findOne("1")).thenReturn(availabilityStorage);
        availabilityDto = availabilityService.findAvailabilityById("1");

        //Assert
        assertEquals("h3y44h5sk58f8sdf48f", availabilityDto.getId());
    }

    @Test
    public void availability_service_should_return_availability_by_application_id_with_correct_model(){
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationId("1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findAvailabilitiesByApplicationId("1");

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_10minutes_availabilities_with_correct_model(){
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMinutes(HubbleConstants.TEN_MINUTES)).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findLast10MinutesAvailabilities();

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_hour_availabilities_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMinutes(HubbleConstants.ONE_HOUR)).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findLastHourAvailabilities();

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }
    
    @Test
    public void availability_service_should_return_last_day_availabilities_with_correct_model(){
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMinutes(HubbleConstants.ONE_DAY)).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findLastDayAvailabilities();

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_month_availabilities_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByDurationMonths(HubbleConstants.ONE_MONTH)).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findLastMonthAvailabilities();

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_10minutes_availabilities_by_applicationid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.TEN_MINUTES, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findLast10MinutesAvailabilitiesByApplicationId("1");

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_hour_availabilities_by_applicationid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.ONE_HOUR, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findLastHourAvailabilitiesByApplicationId("1");

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_day_availabilities_by_applicationid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.ONE_DAY, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findLastDayAvailabilitiesByApplicationId("1");

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_month_availabilities_by_applicationid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(HubbleConstants.ONE_MONTH, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findLastMonthAvailabilitiesByApplicationId("1");

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }
    
    @Test
    public void availability_service_should_calculate_last_10minutes_application_availability_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.TEN_MINUTES, applicationId)).thenReturn(availabilityStorageList);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = availabilityService.calculateLast10MinutesAverageApplicationAvailability(applicationId).getAverage();

        //Assert
        assertEquals(75, average);
    }
    
    @Test
    public void availability_service_should_calculate_last_day_application_availability_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.ONE_DAY, applicationId)).thenReturn(availabilityStorageList);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = availabilityService.calculateLastDayAverageApplicationAvailability(applicationId).getAverage();

        //Assert
        assertEquals(75, average);
    }

    @Test
    public void availability_service_should_return_negative1_when_average_calculation_encounters_no_data(){
        //Assign
        Integer average;
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.TEN_MINUTES, applicationId)).thenReturn(null);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = availabilityService.calculateLastHourAverageApplicationAvailability(applicationId).getAverage();

        //Assert
        assertEquals(null, average);
    }

    @Test
    public void availability_service_should_calculate_last_hour_application_availability_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMinutes(HubbleConstants.ONE_HOUR, applicationId)).thenReturn(availabilityStorageList);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = availabilityService.calculateLastHourAverageApplicationAvailability(applicationId).getAverage();

        //Assert
        assertEquals(75, average);
    }
    
    @Test
    public void availability_service_should_calculate_last_month_application_availability_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByApplicationIdAndDurationMonths(HubbleConstants.ONE_MONTH, applicationId)).thenReturn(availabilityStorageList);
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        average = availabilityService.calculateLastMonthAverageApplicationAvailability(applicationId).getAverage();

        //Assert
        assertEquals(75, average);
    }

    @Test
    public void service_should_return_application_by_id(){
        //Assign
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();
        String applicationId = "b566958ec4ff28028672780d15edcf56";

        //Act
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);

        //Assert
        assertEquals("BancoRipley - HomeBanking", availabilityService.findApplicationById(applicationId).getApplicationName());
    }

    @Test
    public void service_should_return_transaction_by_id(){
        //Assign
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        String transactionId = "2eae220e082697be3a0646400e5b54fa";

        //Act
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);

        //Assert
        assertEquals("Auntenticacion Biometrica", availabilityService.findTransactionById(transactionId).getTransactionName());
    }

    @Test
    public void service_should_retrieve_all_transactions_by_an_application_id(){
        //Assign
        ApplicationStorage applicationStorage = new AvailabilityHelper().mockApplicationStorage();
        String applicationId = "b566958ec4ff28028672780d15edcf56";
        List<TransactionDto> transactionDtoList = new ArrayList();

        //Act
        when(applicationRepository.findApplicationById(applicationId)).thenReturn(applicationStorage);
        transactionDtoList = availabilityService.findTransactionsByApplicationId(applicationId);

        //Assert
        assertEquals(2, transactionDtoList.size());
    }

    @Test
    public void service_should_return_all_applications(){
        //Assign
        List<ApplicationStorage> applicationStorageList = new ArrayList();
        List<ApplicationDto> applicationDtoList = new ArrayList();

        //Act
        applicationStorageList.add(new AvailabilityHelper().mockApplicationStorage());
        when(applicationRepository.findAllApplications()).thenReturn(applicationStorageList);
        applicationDtoList = availabilityService.findAllApplications();

        //Assert
        assertEquals(1, applicationDtoList.size());
    }

    @Test
    public void availability_service_should_return_availability_by_transaction_id_with_correct_model(){
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionId("1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findAvailabilitiesByTransactionId("1");

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_10minutes_availabilities_by_transactionid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.TEN_MINUTES, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findLast10MinutesAvailabilitiesByTransactionId("1");

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_hour_availabilities_by_transactionid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.ONE_HOUR, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findLastHourAvailabilitiesByTransactionId("1");

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_calculate_last_10minutes_transaction_availability_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.TEN_MINUTES, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionId)).thenReturn(parentApplicationStorage);
        average = availabilityService.calculateLast10MinutesAverageTransactionAvailability(transactionId).getAverage();

        //Assert
        assertEquals(75, average);
    }

    @Test
    public void availability_service_should_calculate_last_hour_transaction_availability_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.ONE_HOUR, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionId)).thenReturn(parentApplicationStorage);
        average = availabilityService.calculateLastHourAverageTransactionAvailability(transactionId).getAverage();

        //Assert
        assertEquals(75, average);
    }
    
    @Test
    public void availability_service_should_return_last_day_availabilities_by_transactionid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.ONE_DAY, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findLastDayAvailabilitiesByTransactionId("1");

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_Month_availabilities_by_transactionid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(HubbleConstants.ONE_MONTH, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findLastMonthAvailabilitiesByTransactionId("1");

        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_calculate_last_day_transaction_availability_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMinutes(HubbleConstants.ONE_DAY, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionId)).thenReturn(parentApplicationStorage);
        average = availabilityService.calculateLastDayAverageTransactionAvailability(transactionId).getAverage();

        //Assert
        assertEquals(75, average);
    }

    @Test
    public void availability_service_should_calculate_last_month_transaction_availability_average(){
        //Assign
        int average;
        availabilityStorageList = availabilityHelper.mockData();
        String transactionId = "2eae220e082697be3a0646400e5b54fa";
        TransactionStorage transactionStorage = new AvailabilityHelper().mockTransactionStorage().get(0);
        ApplicationStorage parentApplicationStorage = new AvailabilityHelper().mockApplicationStorage();

        //Act
        when(availabilityRepository.findAvailabilitiesByTransactionIdAndDurationMonths(HubbleConstants.ONE_MONTH, transactionId)).thenReturn(availabilityStorageList);
        when(transactionRepository.findTransactionById(transactionId)).thenReturn(transactionStorage);
        when(applicationRepository.findApplicationByTransactionId(transactionId)).thenReturn(parentApplicationStorage);
        average = availabilityService.calculateLastMonthAverageTransactionAvailability(transactionId).getAverage();

        //Assert
        assertEquals(75, average);
    }
   
}
