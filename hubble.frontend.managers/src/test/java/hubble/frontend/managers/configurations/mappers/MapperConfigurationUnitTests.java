package hubble.frontend.managers.configurations.mappers;

import hubble.backend.business.services.models.AvailabilityApplicationAvgDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.core.enums.MonitoringFields;
import hubble.frontend.managers.models.aggregations.AvailabilityBusinessApplicationAvg;
import hubble.frontend.managers.models.collections.Availability;
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

    AvailabilityMapperConfiguration mapperAvailability = new AvailabilityMapperConfiguration();
    ApplicationMapperConfiguration mapperApplication = new ApplicationMapperConfiguration();
    TransactionMapperConfiguration mapperTransaction = new TransactionMapperConfiguration();
    AvailabilityApplicationAvgMapperConfiguration mapperAvailabilityApplicationAvg = new AvailabilityApplicationAvgMapperConfiguration();

    @Test
    public void validate_availability_mapper_from_availabilitydto_to_availability() {
        mapperAvailability.getMapper().validate();
    }

    @Test
    public void mapper_configuration_should_map_availabilityDto_to_availability() {
        //Assign
        AvailabilityDto availabilityDto = mock(AvailabilityDto.class);

        //Act
        when(availabilityDto.getId()).thenReturn("123");
        when(availabilityDto.getApplicationName()).thenReturn("Application Name");
        when(availabilityDto.getTransactionName()).thenReturn("Transaction Name");
        when(availabilityDto.getLocationName()).thenReturn("Location Name");
        when(availabilityDto.getScriptName()).thenReturn("Script Name");
        when(availabilityDto.getAvailabilityStatus()).thenReturn("No_Data");

        Availability availability = mapperAvailability.mapToAvailability(availabilityDto);

        //Assert
        assertEquals("123", availability.getId());
        assertEquals("Application Name", availability.getBusinessApplication().getName());
        assertEquals("Transaction Name", availability.getTransaction().getName());
        assertEquals("Location Name", availability.getLocation().getName());
        assertEquals("Script Name", availability.getTransaction().getScriptName());
        assertEquals("No_Data", availability.getStatus().toString());
    }

    @Test
    public void mapper_configuration_should_map_availabilityApplicationAvgDto_to_AvailabilityBusinessApplicationAvg() {
        //Assign
        AvailabilityApplicationAvgDto availabilityAvgDto = mock(AvailabilityApplicationAvgDto.class);

        //Act
        when(availabilityAvgDto.getApplicationId()).thenReturn("123");
        when(availabilityAvgDto.getApplicationName()).thenReturn("Application Name");
        when(availabilityAvgDto.getAvailabilityThreshold()).thenReturn(90);
        when(availabilityAvgDto.getAverage()).thenReturn(7510);
        when(availabilityAvgDto.getCriticalThreshold()).thenReturn(12000);
        when(availabilityAvgDto.getOkThreshold()).thenReturn(8000);
        when(availabilityAvgDto.getOutlierThreshold()).thenReturn(45000);
        when(availabilityAvgDto.getStatus()).thenReturn(MonitoringFields.STATUS.NO_DATA);
        when(availabilityAvgDto.getTimeZoneId()).thenReturn("1");

        AvailabilityBusinessApplicationAvg availabilityApplicationAvg = mapperAvailabilityApplicationAvg.mapToAvailabilityApplicationAvg(availabilityAvgDto);

        //Assert
        assertEquals("123", availabilityApplicationAvg.getBusinessApplication().getId());
        assertEquals("Application Name", availabilityApplicationAvg.getBusinessApplication().getName());
        assertEquals(90, availabilityApplicationAvg.getBusinessApplication().getAvailabilityThreshold());
        //assertEquals(75.10f, availabilityApplicationAvg.getAverage());
        //assertEquals(12000, availabilityApplicationAvg.getBusinessApplication().getDefaultPerformanceCritivalThreshold());
        //assertEquals(8000, availabilityApplicationAvg.getBusinessApplication().getDefaultPerformanceOkThreshold());
        //assertEquals(45000, availabilityApplicationAvg.getBusinessApplication().getAvailabilityThreshold());
        assertEquals("No_Data", availabilityApplicationAvg.getStatus().toString());
        assertEquals("1", availabilityApplicationAvg.getBusinessApplication().getTimeZoneId());
    }
    /*
    @Test
    public void mapper_configuration_should_map_availability_transaction_avg() {
        //Assign
        AvailabilityTransactionAvgDto availabilityAvgDto = mock(AvailabilityTransactionAvgDto.class);

        //Act
        when(availabilityAvgDto.getApplicationId()).thenReturn("123");
        when(availabilityAvgDto.getTransactionName()).thenReturn("Transaction Name");
        when(availabilityAvgDto.getAverage()).thenReturn(75);
        when(availabilityAvgDto.getCriticalThreshold()).thenReturn(12000);
        when(availabilityAvgDto.getOkThreshold()).thenReturn(8000);
        when(availabilityAvgDto.getStatus()).thenReturn(MonitoringFields.STATUS.NO_DATA);
        when(availabilityAvgDto.getTransactionType()).thenReturn("Script");
        when(availabilityAvgDto.getScriptName()).thenReturn("Script Name");

        AvailabilityTransactionAvg availabilityTransactionAvg = mapperAvailability.mapToAvailabilityTransactionAvg(availabilityAvgDto);

        //Assert
        assertEquals("123", availabilityTransactionAvg.getApplicationId());
        assertEquals("Transaction Name", availabilityTransactionAvg.getTransactionName());
        assertEquals(75, availabilityTransactionAvg.getAverage());
        assertEquals(12000, availabilityTransactionAvg.getCriticalThreshold());
        assertEquals(8000, availabilityTransactionAvg.getOkThreshold());
        assertEquals("No_Data", availabilityTransactionAvg.getStatus().toString());
        assertEquals("Script", availabilityTransactionAvg.getTransactionType());
        assertEquals("Script Name", availabilityTransactionAvg.getScriptName());
    }

    @Test
    public void appliation_mapper_should_map_to_business_application(){
        //Assign
        ApplicationDto applicationDto = mock(ApplicationDto.class);

        //Act
        when(applicationDto.getApplicationId()).thenReturn("123");
        when(applicationDto.getApplicationName()).thenReturn("Application Name");
        BusinessApplication businessApplication = mapperApplication.mapToBusinessApplication(applicationDto);

        //Assert
        //assertEquals("123", businessApplication.getId());
        //assertEquals("Application Name", businessApplication.getBusinessApplicationName());
        //assertEquals("Application Name", businessApplication.getBusinessApplicationDisplayName());
    }

    @Test
    public void application_mapper_should_map_to_business_transaction(){
        //Assign
        TransactionDto transactionDto = mock(TransactionDto.class);

        //Act
        when(transactionDto.getTransactionId()).thenReturn("123");
        when(transactionDto.getTransactionName()).thenReturn("Transaction Name");
        Transaction businessTransaction = mapperTransaction.mapToBusinessTransaction(transactionDto);

        //Assert
        assertEquals("123", businessTransaction.getId());
        assertEquals("Transaction Name", businessTransaction.getBusinessTransactionName());
        assertEquals("Transaction Name", businessTransaction.getBusinessTransactionDisplayName());
    }
     */
}
