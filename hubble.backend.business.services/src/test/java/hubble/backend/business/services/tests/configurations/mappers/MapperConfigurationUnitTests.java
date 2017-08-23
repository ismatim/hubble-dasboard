package hubble.backend.business.services.tests.configurations.mappers;

import hubble.backend.business.services.configurations.mappers.MapperConfiguration;
import hubble.backend.business.services.models.ApplicationDto;
import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.models.TransactionAvgDto;
import hubble.backend.business.services.models.TransactionDto;
import hubble.backend.business.services.tests.AvailabilityHelper;
import hubble.backend.storage.models.ApplicationStorage;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.storage.models.TransactionStorage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class MapperConfigurationUnitTests {

    MapperConfiguration mapperConfiguration = new MapperConfiguration();
    AvailabilityHelper availabilityHelper = new AvailabilityHelper();

    @Test
    public void mapper_should_map_availabilitystorage_to_availabilitydto() {
        AvailabilityStorage availabilityStorage = mock(AvailabilityStorage.class);

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

        AvailabilityDto availabilityDto = mapperConfiguration.getMapper().map(availabilityStorage, AvailabilityDto.class);

        Assert.assertEquals(availabilityStorage.getApplicationId(), availabilityDto.getApplicationId());
        Assert.assertEquals(availabilityStorage.getApplicationName(), availabilityDto.getApplicationName());
        Assert.assertEquals(availabilityStorage.getAvailabilityStatus(), availabilityDto.getAvailabilityStatus());
        Assert.assertEquals(availabilityStorage.getId(), availabilityDto.getId());
        Assert.assertEquals(availabilityStorage.getLocationId(), availabilityDto.getLocationId());
        Assert.assertEquals(availabilityStorage.getLocationName(), availabilityDto.getLocationName());
        Assert.assertEquals(availabilityStorage.getNumberOfErrors(), availabilityDto.getNumberOfErrors());
        Assert.assertEquals(availabilityStorage.getProviderOrigin(), availabilityDto.getProviderOrigin());
        Assert.assertEquals(availabilityStorage.getTransactionName(), availabilityDto.getTransactionName());
        Assert.assertEquals(availabilityStorage.getTimeStamp(), availabilityDto.getTimeStamp());
        Assert.assertEquals(availabilityStorage.getTransactionId(), availabilityDto.getTransactionId());
        Assert.assertEquals(availabilityStorage.getServerName(), availabilityDto.getServerName());
        Assert.assertEquals(availabilityStorage.getScriptName(), availabilityDto.getScriptName());
    }

    @Test
    public void mapper_convert_availabilityStorage_to_availabilityDto_model() {
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
    public void mapper_convert_availabilityStorageList_to_availabilityDtoList_model() {
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

    @Test
    public void mapper_should_map_applicationStorage_to_applicationDto() {
        ApplicationStorage applicationStorage = mock(ApplicationStorage.class);

        when(applicationStorage.getApplicationId()).thenReturn("1234");
        when(applicationStorage.getApplicationName()).thenReturn("BancoRipley - HomeBanking");
        when(applicationStorage.getAvailabilityThreshold()).thenReturn(90);
        when(applicationStorage.getCriticalThreshold()).thenReturn(12000);
        when(applicationStorage.isActive()).thenReturn(true);
        when(applicationStorage.getLocations()).thenReturn(null);
        when(applicationStorage.getOkThreshold()).thenReturn(8000);
        when(applicationStorage.getOutlierThreshold()).thenReturn(45000);
        when(applicationStorage.getTimeZoneId()).thenReturn("1");
        when(applicationStorage.getTransactions()).thenReturn(null);

        ApplicationDto applicationDto = mapperConfiguration.mapToApplicationDto(applicationStorage);

        assertEquals("1234", applicationDto.getApplicationId());
        assertEquals("BancoRipley - HomeBanking", applicationDto.getApplicationName());
        assertEquals(90, applicationDto.getAvailabilityThreshold());
        assertEquals(12000, applicationDto.getCriticalThreshold());
        assertEquals(true, applicationDto.isActive());
        assertEquals(null, applicationDto.getLocations());
        assertEquals(8000, applicationDto.getOkThreshold());
        assertEquals(45000, applicationDto.getOutlierThreshold());
        assertEquals("1", applicationDto.getTimeZoneId());
        assertEquals(null, applicationDto.getTransactions());
    }

    @Test
    public void mapper_should_map_transactionStorage_to_transactionDto() {
        TransactionStorage transactionStorage = mock(TransactionStorage.class);

        when(transactionStorage.getTransactionId()).thenReturn("1234");
        when(transactionStorage.getTransactionName()).thenReturn("Transaction Name");
        when(transactionStorage.getCriticalThreshold()).thenReturn(12000);
        when(transactionStorage.isAssigned()).thenReturn(true);
        when(transactionStorage.getOkThreshold()).thenReturn(8000);
        when(transactionStorage.getTransactionType()).thenReturn("script");
        when(transactionStorage.getScriptName()).thenReturn("Script Name");

        TransactionDto transactionDto = mapperConfiguration.mapToTransactionDto(transactionStorage);

        assertEquals("1234", transactionDto.getTransactionId());
        assertEquals("Transaction Name", transactionDto.getTransactionName());
        assertEquals(12000, transactionDto.getCriticalThreshold());
        assertEquals(true, transactionDto.isAssigned());
        assertEquals(8000, transactionDto.getOkThreshold());
        assertEquals("script", transactionDto.getTransactionType());
        assertEquals("Script Name", transactionDto.getScriptName());
    }

    @Test
    public void mapper_convert_applicationStorageList_to_applicationDtoList_model() {
        //Assign
        List<ApplicationStorage> applicationStorageList = new ArrayList();
        applicationStorageList.add(availabilityHelper.mockApplicationStorage());

        //Act
        List<ApplicationDto> applicationDtoList = mapperConfiguration.mapToApplicationDtoList(applicationStorageList);

        //Assert
        assertNotNull(applicationDtoList);
        assertEquals("b566958ec4ff28028672780d15edcf56", applicationDtoList.get(0).getApplicationId());
        assertEquals("BancoRipley - HomeBanking", applicationDtoList.get(0).getApplicationName());
    }

    @Test
    public void mapper_convert_transactionStorageList_to_transactionDtoList_model() {
        //Assign
        List<TransactionStorage> transactionStorageList = availabilityHelper.mockTransactionStorage();

        //Act
        List<TransactionDto> transactionDtoList = mapperConfiguration.mapToTransactionDtoList(transactionStorageList);

        //Assert
        assertNotNull(transactionDtoList);
        assertEquals("2eae220e082697be3a0646400e5b54fa", transactionDtoList.get(0).getTransactionId());
        assertEquals("Auntenticacion Biometrica", transactionDtoList.get(0).getTransactionName());
    }

    @Test
    public void mapper_should_map_transactionStorage_to_transactionAvailabilityAverage() {
        TransactionStorage transactionStorage = mock(TransactionStorage.class);

        when(transactionStorage.getTransactionId()).thenReturn("1234");
        when(transactionStorage.getTransactionName()).thenReturn("Transaction Name");
        when(transactionStorage.getCriticalThreshold()).thenReturn(12000);
        when(transactionStorage.isAssigned()).thenReturn(true);
        when(transactionStorage.getOkThreshold()).thenReturn(8000);
        when(transactionStorage.getTransactionType()).thenReturn("script");
        when(transactionStorage.getScriptName()).thenReturn("Script Name");

        TransactionAvgDto transactionAvailabilityAvg = mapperConfiguration.mapToTransactionAvailabilityAvg(transactionStorage);

        assertEquals("1234", transactionAvailabilityAvg.getTransactionId());
        assertEquals("Transaction Name", transactionAvailabilityAvg.getTransactionName());
        assertEquals(12000, transactionAvailabilityAvg.getCriticalThreshold());
        assertEquals(true, transactionAvailabilityAvg.isAssigned());
        assertEquals(8000, transactionAvailabilityAvg.getOkThreshold());
        assertEquals("script", transactionAvailabilityAvg.getTransactionType());
        assertEquals("Script Name", transactionAvailabilityAvg.getScriptName());
    }

    @Test
    public void mapper_should_map_transactionStorage_to_applicationAvailabilityAverage() {
        ApplicationStorage applicationStorage = mock(ApplicationStorage.class);

        when(applicationStorage.getApplicationId()).thenReturn("1234");
        when(applicationStorage.getApplicationName()).thenReturn("BancoRipley - HomeBanking");
        when(applicationStorage.getAvailabilityThreshold()).thenReturn(90);
        when(applicationStorage.getCriticalThreshold()).thenReturn(12000);
        when(applicationStorage.isActive()).thenReturn(true);
        when(applicationStorage.getLocations()).thenReturn(null);
        when(applicationStorage.getOkThreshold()).thenReturn(8000);
        when(applicationStorage.getOutlierThreshold()).thenReturn(45000);
        when(applicationStorage.getTimeZoneId()).thenReturn("1");
        when(applicationStorage.getTransactions()).thenReturn(null);

        ApplicationAvgDto applicationAvailabilityAvg = mapperConfiguration.mapToApplicationAvailabilityAvg(applicationStorage);

        assertEquals("1234", applicationAvailabilityAvg.getApplicationId());
        assertEquals("BancoRipley - HomeBanking", applicationAvailabilityAvg.getApplicationName());
        assertEquals(90, applicationAvailabilityAvg.getAvailabilityThreshold());
        assertEquals(12000, applicationAvailabilityAvg.getCriticalThreshold());
        assertEquals(true, applicationAvailabilityAvg.isActive());
        assertEquals(null, applicationAvailabilityAvg.getLocations());
        assertEquals(8000, applicationAvailabilityAvg.getOkThreshold());
        assertEquals(45000, applicationAvailabilityAvg.getOutlierThreshold());
        assertEquals("1", applicationAvailabilityAvg.getTimeZoneId());
        assertEquals(null, applicationAvailabilityAvg.getTransactions());
    }

    @Test
    public void mapper_convert_transactionStorageList_to_transactionAvailabilityAvgList_model() {
        //Assign
        List<TransactionStorage> transactionStorageList = availabilityHelper.mockTransactionStorage();

        //Act
        List<TransactionAvgDto> transactionAvailabilityAvgList = mapperConfiguration.mapToTransactionAvailabilityAvgList(transactionStorageList);

        //Assert
        assertNotNull(transactionAvailabilityAvgList);
        assertEquals("2eae220e082697be3a0646400e5b54fa", transactionAvailabilityAvgList.get(0).getTransactionId());
        assertEquals("Auntenticacion Biometrica", transactionAvailabilityAvgList.get(0).getTransactionName());
    }

    @Test
    public void mapper_convert_applicationStorageList_to_applicationAvailabilityAvgList_model() {
        //Assign
        List<ApplicationStorage> applicationStorageList = new ArrayList();
        applicationStorageList.add(availabilityHelper.mockApplicationStorage());

        //Act
        List<ApplicationAvgDto> applicationAvailabilityAvgList = mapperConfiguration.mapToApplicationAvailabilityAvgList(applicationStorageList);

        //Assert
        assertNotNull(applicationAvailabilityAvgList);
        assertEquals("b566958ec4ff28028672780d15edcf56", applicationAvailabilityAvgList.get(0).getApplicationId());
        assertEquals("BancoRipley - HomeBanking", applicationAvailabilityAvgList.get(0).getApplicationName());
    }
}
