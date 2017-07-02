package hubble.backend.providers.tests.configurations;

import hubble.backend.providers.configurations.mappers.apppulse.MapperConfiguration;
import hubble.backend.providers.models.apppulse.AvailabilityProviderModel;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.providers.tests.AppPulseBaseUnitTests;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MapperConfigurationUnitTests extends AppPulseBaseUnitTests {

    MapperConfiguration mapperConfifuration = new MapperConfiguration();

    @Test
    public void AppPulseActiveParser_convert_apppulse_model_provider_to_storage() {

        //Assign
        AvailabilityProviderModel appPulseActivity = this.getFakeAppPulseProviderModel();

        //Act
        List<AvailabilityStorage> availabilityStorageList = mapperConfifuration.mapToAvailabilitiesStorage(appPulseActivity);

        //Assert
        assertNotNull(availabilityStorageList);
        assertTrue(availabilityStorageList.get(0).getApplicationId() == "1");
        assertTrue(availabilityStorageList.get(0).getApplicationName() == "fake-name");
        assertTrue(availabilityStorageList.get(0).getAvailabilityStatus()== "fake-status");
    }
}
