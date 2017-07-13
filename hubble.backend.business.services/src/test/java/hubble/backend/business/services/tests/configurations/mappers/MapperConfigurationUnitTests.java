package hubble.backend.business.services.tests.configurations.mappers;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.tests.AvailabilityHelper;
import hubble.backend.storage.models.AvailabilityStorage;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class MapperConfigurationUnitTests {

    public MapperConfigurationUnitTests() {
    }

    MapperConfiguration mapperConfiguration = new MapperConfiguration();
    AvailabilityHelper availabilityHelper = new AvailabilityHelper();

      @Test
    public void mapper_should_map_availabilitystorage_to_availabilitydto(){
        AvailabilityStorage availabilityStorage = new Mockito().mock(AvailabilityStorage.class);

        when(availabilityStorage.getApplicationId()).thenReturn("1");
        when(availabilityStorage.getApplicationName()).thenReturn("Ripley - HomeBanking");
        when(availabilityStorage.getAvailabilityStatus()).thenReturn("Success");
        when(availabilityStorage.getId()).thenReturn("asdasd54142356");
        when(availabilityStorage.getLocationId()).thenReturn("1");
        when(availabilityStorage.getLocationName()).thenReturn("Sonda Interna");
        when(availabilityStorage.getNumberOfErrors()).thenReturn(0);
        when(availabilityStorage.getProviderOrigin()).thenReturn("AppPulse Active");
        when(availabilityStorage.getTransactionId()).thenReturn("2");
        when(availabilityStorage.getTransactionName()).thenReturn("HomeBanking - Login");
        when(availabilityStorage.getTimeStamp()).thenReturn(new Date());
        when(availabilityStorage.getServerName()).thenReturn("Banco Ripley - App Pulse Active");
        when(availabilityStorage.getScriptName()).thenReturn("homebanking");
        when(availabilityStorage.getErrors()).thenReturn(null);

        AvailabilityDto availabilityDto = mapperConfiguration.getMapper().map(availabilityStorage,AvailabilityDto.class);

        Assert.assertEquals(availabilityStorage.getApplicationId(),availabilityDto.getApplicationId());
        Assert.assertEquals(availabilityStorage.getApplicationName(),availabilityDto.getApplicationName());
        Assert.assertEquals(availabilityStorage.getAvailabilityStatus(),availabilityDto.getAvailabilityStatus());
        Assert.assertEquals(availabilityStorage.getId(),availabilityDto.getId());
        Assert.assertEquals(availabilityStorage.getLocationId(),availabilityDto.getLocationId());
        Assert.assertEquals(availabilityStorage.getLocationName(),availabilityDto.getLocationName());
        Assert.assertEquals(availabilityStorage.getNumberOfErrors(),availabilityDto.getNumberOfErrors());
        Assert.assertEquals(availabilityStorage.getProviderOrigin(),availabilityDto.getProviderOrigin());
        Assert.assertEquals(availabilityStorage.getTransactionName(),availabilityDto.getTransactionName());
        Assert.assertEquals(availabilityStorage.getTimeStamp(),availabilityDto.getTimeStamp());
        Assert.assertEquals(availabilityStorage.getTransactionId(),availabilityDto.getTransactionId());
        Assert.assertEquals(availabilityStorage.getServerName(),availabilityDto.getServerName());
        Assert.assertEquals(availabilityStorage.getScriptName(),availabilityDto.getScriptName());

    }

    @Test
    public void appPulseAvailabilityService_convert_availabilityStorage_to_availabilityDto_model(){
        //Assign
        AvailabilityStorage availabilityStorage = availabilityHelper.mockOneAvailabilityStorage();

        //Act
        AvailabilityDto availabilityDto = mapperConfiguration.mapToAvailabilityDto(availabilityStorage);

        //Assert
        assertNotNull(availabilityDto);
        assertEquals("1", availabilityDto.getApplicationId());
        assertEquals("BancoRipley - HomeBanking", availabilityDto.getApplicationName());
        assertEquals("Washington DC, AT&T", availabilityDto.getLocationName());
    }

     @Test
    public void appPulseAvailabilityService_convert_availabilityStorageList_to_availabilityDtoList_model(){
        //Assign
        List<AvailabilityStorage> availabilityStorageList = availabilityHelper.mockData();

        //Act
        List<AvailabilityDto> availabilityDtoList = mapperConfiguration.mapToAvailabilityDtoList(availabilityStorageList);

        //Assert
        assertNotNull(availabilityDtoList);
        assertEquals("1", availabilityDtoList.get(0).getApplicationId());
        assertEquals("BancoRipley - HomeBanking", availabilityDtoList.get(0).getApplicationName());
        assertEquals("Washington DC, AT&T", availabilityDtoList.get(0).getLocationName());
    }
}
