package hubble.backend.providers.tests.parsers;

import hubble.backend.providers.configurations.mappers.apppulse.MapperConfiguration;
import hubble.backend.providers.models.apppulse.AvailabilityProviderModel;
import hubble.backend.providers.parsers.implementations.AppPulseActiveParserImpl;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.providers.tests.AppPulseBaseUnitTests;
import hubble.backend.providers.transports.implementations.AppPulseActiveTransportImpl;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AppPulseActiveParserConvertUnitTests extends AppPulseBaseUnitTests {

    @Spy
    AppPulseActiveTransportImpl appPulseActiveTransport = new AppPulseActiveTransportImpl();
    @Spy
    MapperConfiguration mapperConfifuration = new MapperConfiguration();


    private AppPulseActiveParserImpl appPulseActiveParser;

    @Test
    public void AppPulseActiveParser_convert_apppulse_model_provider_to_storage() {

        //Assign
        appPulseActiveParser = new AppPulseActiveParserImpl(appPulseActiveTransport, mapperConfifuration);
        AvailabilityProviderModel appPulseActivity = this.getFakeAppPulseProviderModel();

        //Act
        List<AvailabilityStorage> availabilityStorageList = appPulseActiveParser.convert(appPulseActivity);

        //Assert
        assertNotNull(availabilityStorageList);
    }
}
