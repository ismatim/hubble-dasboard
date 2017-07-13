package hubble.backend.business.services.tests;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.tests.configurations.ServiceBaseConfigurationTest;
import hubble.backend.business.services.implementations.AvailabilityServiceImpl;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.repositories.AvailabilityRepository;
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
    private AvailabilityRepository repository;
    @Spy
    private MapperConfiguration mapper;

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
        //Act
        availabilityService.findAllAvailabilities();
        //Assert
        verify(repository).findAll();
    }

    @Test
    public void availability_service_should_return_all_availabilities() {
        //Assign
        availabilityStorageList = availabilityHelper.mockData();
        //Act
        when(repository.findAll()).thenReturn(availabilityStorageList);
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
        when(repository.findOne("1")).thenReturn(availabilityStorage);
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
        when(repository.findAvailabilitiesByApplicationId("1")).thenReturn(availabilityStorageList);
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
        when(repository.findAvailabilitiesByDurationMinutes(10)).thenReturn(availabilityStorageList);
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
        when(repository.findAvailabilitiesByDurationMinutes(60)).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findLastHourAvailabilities();
        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

    @Test
    public void availability_service_should_return_last_10minutes_availabilities_by_applicationid_with_correct_model(){
         //Assign
        availabilityStorageList = availabilityHelper.mockData();
        List<AvailabilityDto> availabilityDtoList;
        //Act
        when(repository.findAvailabilitiesByApplicationIdAndDurationMinutes(10, "1")).thenReturn(availabilityStorageList);
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
        when(repository.findAvailabilitiesByApplicationIdAndDurationMinutes(60, "1")).thenReturn(availabilityStorageList);
        availabilityDtoList = availabilityService.findLastHourAvailabilitiesByApplicationId("1");
        //Assert
        assertEquals(4, availabilityDtoList.size());
    }

}
