package hubble.backend.providers.tests.configurations.mappers;

import hubble.backend.providers.configurations.mappers.apppulse.AppPulseMapperConfiguration;
import hubble.backend.providers.models.apppulse.AvailabilityProviderModel;
import hubble.backend.providers.tests.AppPulseBaseUnitTests;
import hubble.backend.storage.models.AvailabilityStorage;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MapperConfigurationUnitTests extends AppPulseBaseUnitTests {

    AppPulseMapperConfiguration mapperConfifuration = new AppPulseMapperConfiguration();

    @Test
    public void AppPulseActiveParser_convert_apppulse_model_provider_to_storage() {

        //Assign
        AvailabilityProviderModel appPulseActivity = MapperConfigurationUnitTests.getFakeAppPulseProviderModel();

        //Act
        List<AvailabilityStorage> availabilityStorageList = mapperConfifuration.mapToAvailabilitiesStorage(appPulseActivity);

        //Assert
        assertNotNull(availabilityStorageList);
        assertTrue(availabilityStorageList.get(0).getApplicationId().equals("1"));
        assertTrue(availabilityStorageList.get(0).getApplicationName().equals("fake-name"));
        assertTrue(availabilityStorageList.get(0).getAvailabilityStatus().equals("fake-status"));
    }
}
