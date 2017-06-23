package hubble.backend.providers.tests.parsers;

import hubble.backend.providers.models.apppulse.AvailabilityProviderModel;
import hubble.backend.providers.parsers.implementations.AppPulseActiveParserImpl;
import hubble.backend.storage.models.AvailabilityStorage;
import hubble.backend.providers.tests.configurations.BaseConfiguration;
import hubble.backend.providers.tests.AppPulseBaseUnitTests;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BaseConfiguration.class)
public class AppPulseActiveParserConvertUnitTests extends AppPulseBaseUnitTests {

    @Autowired
    private AppPulseActiveParserImpl appPulseActiveParser;

    @Test
    public void AppPulseActiveParser_convert_apppulse_model_provider_to_storage() {

        //Assign
        AvailabilityProviderModel appPulseActivity = this.getFakeAppPulseProviderModel();

        //Act
        List<AvailabilityStorage> availabilityStorageList = appPulseActiveParser.convert(appPulseActivity);

        //Assert
        assertNotNull(availabilityStorageList);
    }
}
