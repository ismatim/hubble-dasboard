package hubble.frontend.managers.configurations.mappers;

import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.AvailabilityDto;
import hubble.backend.business.services.models.measures.AvailabilityAverage;
import hubble.backend.core.enums.MonitoringFields;
import hubble.frontend.managers.models.Availability;
import hubble.frontend.managers.models.BusinessApplicationAvg;
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
    ApplicationAvgMapperConfiguration mapperAvailabilityApplicationAvg = new ApplicationAvgMapperConfiguration();

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
        ApplicationAvgDto availabilityAvgDto = mock(ApplicationAvgDto.class);
        AvailabilityAverage availabilityAvgFake = mock(AvailabilityAverage.class);

        //Act
        when(availabilityAvgDto.getApplicationId()).thenReturn("123");
        when(availabilityAvgDto.getApplicationName()).thenReturn("Application Name");
        when(availabilityAvgDto.getAvailabilityThreshold()).thenReturn(90);
        when(availabilityAvgDto.getAvailabilityAverageValue()).thenReturn(7510);
        when(availabilityAvgDto.getCriticalThreshold()).thenReturn(12000);
        when(availabilityAvgDto.getOkThreshold()).thenReturn(8000);
        when(availabilityAvgDto.getOutlierThreshold()).thenReturn(45000);
        when(availabilityAvgDto.getAvailabilityAverage()).thenReturn(availabilityAvgFake);
        when(availabilityAvgFake.getStatus()).thenReturn(MonitoringFields.STATUS.NO_DATA);
        when(availabilityAvgDto.getTimeZoneId()).thenReturn("1");

        BusinessApplicationAvg availabilityApplicationAvg = mapperAvailabilityApplicationAvg.mapToAvailabilityApplicationAvg(availabilityAvgDto);

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
}
